;
;  (C) Copyright 2015, 2016  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns clj-fileutils.fileutils)

(require '[hozumi.rm-rf    :as rm-rf])

(def temporary-name-prefix
    "clojure-fileutils-")

(defn print-slurp-exception
    "Print warning message to the standard output."
    ([filename]
        (println "Warning: cannot read content of the following file:" filename))
    ([directory filename]
        (println "Warning: cannot read content of the following file:" directory "/" filename)))

(defn slurp-
    "Alternative (slurp) implementation that does not throw an exception, but returns nil instead."
    ([filename]
        (try
            (slurp filename)
            (catch Exception e (print-slurp-exception filename))))
    ([directory filename]
        (try
            (slurp (str directory "/" filename))
            (catch Exception e (print-slurp-exception directory filename)))))

(defn new-file
    "Just a shortcut for (new java.io.File filename)."
    ( [filename]
    (new java.io.File filename))
    ( [path filename]
    (new java.io.File path filename)))


(defn remove-temporary-directory
    "Remove (delete) temporary directory that resides in /tmp (at least on Linux)."
    [directory-name]
    (let [file (new java.io.File directory-name)
          abs-path (.getAbsolutePath file)]
        ; make sure we are not going to remove wrong directory!
        (if (.startsWith abs-path "/tmp")
            (rm-rf/rm-r file))))

(defn remove-directory
    [directory]
    (rm-rf/rm-r directory))

(defn mv-file
    "Move or rename one file. On the same filesystem the rename should be atomic."
    [filename1 filename2]
    (let [file1 (new java.io.File filename1)
          file2 (new java.io.File filename2)]
          (.renameTo file1 file2)))

(defn move-file
    "Move or rename one file. On the same filesystem the rename should be atomic."
    [filename1 filename2]
    (mv-file filename1 filename2))

(defn make-temporary-log-file-name
    []
    (let [basedir-name (new java.io.File (System/getProperty "java.io.tmpdir"))
          base-name    temporary-name-prefix
          temp-file    (java.io.File/createTempFile base-name ".log" basedir-name)]
          (.getAbsolutePath temp-file)))

(defn make-temporary-directory
    "Make temporary directory that would reside in /tmp (at least on Linux)."
    []
    (let [basedir-name (System/getProperty "java.io.tmpdir")
          base-name    (str temporary-name-prefix (System/currentTimeMillis))
          temp-dir     (new java.io.File basedir-name base-name)]
        (.mkdir temp-dir)
        temp-dir))

