(ns pull.core)

(declare pull)

(defmulti pull* (fn [_ _ {:keys [type]}] type))

(defmethod pull* :prop
  [m res {:keys [key]}]
  (if-let [v (key m)] (assoc res key v) res))

(defmethod pull* :join
  [m res {:keys [key query]}]
  (if-let [v (pull (key m) query)] 
    (assoc res key v)
    res))

(defn keyword->ast [k]
  {:type :prop
   :key k})

(declare expr->ast)

(defn join->ast [join]
  (let [[k v] (first join)
        ast (expr->ast k)]
    (merge ast {:type :join :query v})))

(defn expr->ast [x]
  (cond
    (keyword? x) (keyword->ast x)
    (map? x)     (join->ast x)
    :else        (throw
                   (ex-info (str "Invalid expression " x)
                            {:type :error/invalid-expression}))))

(defn pull
  "Like Datomic pull but over maps of maps."
  [m pattern]
  (when m
    (reduce (partial pull* m) nil (into [] (map expr->ast) pattern))))
