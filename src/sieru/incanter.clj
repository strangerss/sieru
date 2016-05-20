(ns sieru.incanter
  (:gen-class)
  (:require [incanter.core :as core])
  (:require [incanter.stats  :as stats])
  (:require [incanter.datasets :as datasets])
  (:require [incanter.pdf :as pdf])
  (:require [incanter.charts  :as charts]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;
;;; incanter
;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; (use '(incanter core charts datasets))

(def iris (datasets/get-dataset :iris))
(core/view iris)
(core/$where {:Species "setosa"} iris)
(core/$where {:Petal.Width {:$gt 2}} iris)


(core/with-data (datasets/get-dataset :iris)
  (doto (charts/scatter-plot :Sepal.Length :Sepal.Width :group-by :Species)
    (charts/set-theme :dark)
    core/view))

;; (use '(incanter core charts pdf))
;; (save-pdf (function-plot sin -4 4) "./pdf-chart.pdf")


(core/with-data (datasets/get-dataset :iris)
  (core/view (charts/scatter-plot :Sepal.Length :Sepal.Width :group-by :Species)))

(core/view (charts/bar-chart :Species :Sepal.Length :data iris))

(def airline (datasets/get-dataset :airline-passengers))
(def years (core/sel airline :cols 0))


(def months (core/sel airline :cols 2))
(def passengers (core/sel airline :cols 1))

(core/view (charts/line-chart months passengers :group-by years :legend true))


(let [x (range -3 3 0.1)]
  (core/view (charts/dynamic-xy-plot [mean (range -3 3 0.1)
                          std-dev (range 0.1 10 0.1)]
          [x (stats/pdf-normal x :mean mean :sd std-dev)])))
