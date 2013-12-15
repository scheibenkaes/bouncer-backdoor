(ns backdoor.core-test
  (:use org.httpkit.fake)
  (:require [midje.sweet :refer :all]
            [backdoor.core :refer :all]))

(facts "credential-fn"
       (with-fake-http [verify-url {:body "{\"status\" : \"okay\", \"email\" : \"foo@bar.de\"}" :status 200}]
         (fact "missing assertion returns null"
               (credential-fn nil) => nil?
               (credential-fn {}) => nil?)

         (fact "returns an authentication map on success"
               (let [auth-map {:identity "foo@bar.de" :password nil :roles #{:user}}]
                 (credential-fn {:assertion "foo"}) => auth-map))
         ))

;; TODO *error-fn*
