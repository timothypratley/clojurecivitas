^{:clay {:quarto {:draft true}}}
(ns civitas.explorer.json-ld
  (:require [clojure.data.json :as json]))

(def ^:dynamic *ldns* "civitas")
(def ^:dynamic *base* (str "https://clojurecivitas.github.io/" *ldns* "/"))

(defn notebook-jsonld [{:keys [id title topic level] :as resource}]
  {"@id"    (str *base* "resource/" id),
   "@type"  (str *ldns* ":Notebook"),
   "title"  title,
   "topics" (mapv #(str *ldns* "topic/" (name %)) topic),
   "level"  level})

(defn write-jsonld [target resources]
  (spit target
        (json/write-str
          {:context (str *base* "context.jsonld")
           :graph   (mapv notebook-jsonld resources)})))

(comment
  (write-jsonld "resources.jsonld" resources))
