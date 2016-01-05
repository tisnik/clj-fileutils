;
;  (C) Copyright 2015, 2016  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns clj-fileutils.fileutils)

(defn print-slurp-exception
    "Print warning message to the standard output."
    ([filename]
        (println "Warning: cannot read content of the following file: " filename))
    ([directory filename]
        (println "Warning: cannot read content of the following file: " directory "/" filename)))

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

(defn mv-file
    "Move or rename one file. On the same filesystem the rename should be atomic."
    [filename1 filename2]
    (let [file1 (new java.io.File filename1)
          file2 (new java.io.File filename2)]
          (.renameTo file1 file2)))


