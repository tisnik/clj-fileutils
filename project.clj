;
;  (C) Copyright 2015, 2016, 2017, 2018, 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(defproject org.clojars.tisnik/clj-fileutils "0.4.0-SNAPSHOT"
  :description "Set of functions for manipulating with files and directories."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License",
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-rm-rf "1.0.0-SNAPSHOT"]]
  :plugins [[test2junit "1.1.0"]
            [codox "0.8.11"]
            [lein-cloverage "1.0.7-SNAPSHOT"]])
