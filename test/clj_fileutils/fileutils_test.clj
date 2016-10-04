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

(ns clj-fileutils.fileutils-test
  (:require [clojure.test :refer :all]
            [clj-fileutils.fileutils :refer :all]))


;
; Common functions used by tests.
;

(defn callable?
    "Test if given function-name is bound to the real function."
    [function-name]
    (clojure.test/function? function-name))


;
; Test for function definitions.
;

(deftest test-print-slurp-exception-existence
    "Check that the clj-fileutils.fileutils/print-slurp-exception function definition exists."
    (testing "if the clj-fileutils.fileutils/print-slurp-exception function definition exists."
        (is (callable? 'clj-fileutils.fileutils/print-slurp-exception))))

(deftest test-slurp-existence
    "Check that the clj-fileutils.fileutils/slurp- function definition exists."
    (testing "if the clj-fileutils.fileutils/slurp- function definition exists."
        (is (callable? 'clj-fileutils.fileutils/slurp-))))

(deftest test-new-file-existence
    "Check that the clj-fileutils.fileutils/new-file function definition exists."
    (testing "if the clj-fileutils.fileutils/new-file function definition exists."
        (is (callable? 'clj-fileutils.fileutils/new-file))))

(deftest test-mv-file-existence
    "Check that the clj-fileutils.fileutils/mv-file function definition exists."
    (testing "if the clj-fileutils.fileutils/mv-file function definition exists."
        (is (callable? 'clj-fileutils.fileutils/mv-file))))

(deftest test-move-file-existence
    "Check that the clj-fileutils.fileutils/move-file function definition exists."
    (testing "if the clj-fileutils.fileutils/move-file function definition exists."
        (is (callable? 'clj-fileutils.fileutils/move-file))))

(deftest test-make-temporary-log-file-name-existence
    "Check that the clj-fileutils.fileutils/make-temporary-log-file-name function definition exists."
    (testing "if the clj-fileutils.fileutils/make-temporary-log-file-name function definition exists."
        (is (callable? 'clj-fileutils.fileutils/make-temporary-log-file-name))))

(deftest test-make-temporary-directory-existence
    "Check that the clj-fileutils.fileutils/make-temporary-directory function definition exists."
    (testing "if the clj-fileutils.fileutils/make-temporary-directory function definition exists."
        (is (callable? 'clj-fileutils.fileutils/make-temporary-directory))))

;
; Actual tests.
;

(def msg-start
    "Warning: cannot read content of the following file: ")

(deftest test-new-file-1
    "Check the function clj-fileutils.fileutils/new-file."
    (testing "Check the function clj-fileutils.fileutils/new-file."
        (are [x y] (= x y)
            ""            (.toString (new-file ""))
            "abc"         (.toString (new-file "abc"))
            "abc.def"     (.toString (new-file "abc.def"))
            "abc/def"     (.toString (new-file "abc/def"))
            "abc/def/xyz" (.toString (new-file "abc/def/xyz")))))

(deftest test-new-file-2
    "Check the function clj-fileutils.fileutils/new-file."
    (testing "Check the function clj-fileutils.fileutils/new-file."
        (are [x y] (= x y)
            "/"           (.toString (new-file "" ""))
            "abc"         (.toString (new-file "abc" ""))
            "abc.def"     (.toString (new-file "abc.def" ""))
            "abc/def"     (.toString (new-file "abc/def" ""))
            "abc/def/xyz" (.toString (new-file "abc/def/xyz" "")))))

(deftest test-new-file-3
    "Check the function clj-fileutils.fileutils/new-file."
    (testing "Check the function clj-fileutils.fileutils/new-file."
        (are [x y] (= x y)
            "/"            (.toString (new-file "" ""))
            "/abc"         (.toString (new-file "" "abc"))
            "/abc.def"     (.toString (new-file "" "abc.def"))
            "/abc/def"     (.toString (new-file "" "abc/def"))
            "/abc/def/xyz" (.toString (new-file "" "abc/def/xyz")))))

(deftest test-new-file-4
    "Check the function clj-fileutils.fileutils/new-file."
    (testing "Check the function clj-fileutils.fileutils/new-file."
        (are [x y] (= x y)
            "abc/def"         (.toString (new-file "abc" "def"))
            "abc/abc.def"     (.toString (new-file "abc" "abc.def")))))

(deftest test-new-file-negative
    "Check the function clj-fileutils.fileutils/new-file."
    (testing "Check the function clj-fileutils.fileutils/new-file."
        (is (thrown? NullPointerException (new-file nil)))
        (is (thrown? NullPointerException (new-file "" nil)))
        (is (thrown? NullPointerException (new-file "abc" nil)))
        (is (thrown? NullPointerException (new-file nil nil)))))

(deftest test-make-temporary-directory-1
    "Check the function clj-fileutils.fileutils/make-temporary-directory."
    (testing "Check the function clj-fileutils.fileutils/make-temporary-directory."
        (let [tempdir (make-temporary-directory)]
            (println "Temporary directory" tempdir)
            (is (not (nil? tempdir)))
            (is (.startsWith (.getPath tempdir) "/tmp"))
            (if tempdir
                (remove-temporary-directory (.getPath tempdir))))))

(deftest test-make-temporary-directory-2
    "Check the function clj-fileutils.fileutils/make-temporary-directory."
    (testing "Check the function clj-fileutils.fileutils/make-temporary-directory."
        (let [tempdir (make-temporary-directory)]
            (println "Temporary directory" tempdir)
            (is (.isDirectory tempdir))
            (is (not (.isFile tempdir)))
            (if tempdir
                (remove-temporary-directory (.getPath tempdir))))))

(deftest test-make-temporary-log-file-name
    "Check the function clj-fileutils.fileutils/make-temporary-log-file-name."
    (testing "Check the function clj-fileutils.fileutils/make-temporary-log-file-name."
        (let [templog (make-temporary-log-file-name)]
            (println "Temporary log file" templog)
            (is (not (nil? templog)))
            (is (.startsWith templog "/tmp"))
            (if templog
                (-> (new java.io.File templog) .delete)))))

