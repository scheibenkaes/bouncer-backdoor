(ns backdoor.core
  (:require [org.httpkit.client :as http]
            [cheshire.core :as json]))

(defn workflow []
  (fn [req]))

(def ^:const verify-url "https://verifier.login.persona.org/verify")

(def ^:dynamic *http-opts* {:insecure? false
                            :timeout 1000
                            :form-params {:assertion nil
                                          :audience nil}})

(defn credential-fn [{:keys [assertion audience]}]
  (when assertion
    (let [opts (-> *http-opts*
                   (update-in [:form-params] assoc :assertion assertion)
                   (update-in [:form-params] assoc :audience audience))
          {:keys [error status body]} @(http/post verify-url opts)]
      (when (= status 200)
        (let [{:keys [email status]} (json/parse-string body true)]
          (when (= status "okay")
            {:identity email :password nil :roles #{:user}}))))))
