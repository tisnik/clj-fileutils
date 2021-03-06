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
  (testing
    "if the clj-fileutils.fileutils/print-slurp-exception function definition exists."
    (is (callable? 'clj-fileutils.fileutils/print-slurp-exception))))

(deftest test-slurp-existence
  "Check that the clj-fileutils.fileutils/slurp- function definition exists."
  (testing "if the clj-fileutils.fileutils/slurp- function definition exists."
           (is (callable? 'clj-fileutils.fileutils/slurp-))))

(deftest test-new-file-existence
  "Check that the clj-fileutils.fileutils/new-file function definition exists."
  (testing "if the clj-fileutils.fileutils/new-file function definition exists."
           (is (callable? 'clj-fileutils.fileutils/new-file))))

(deftest test-remove-temporary-directory-existence
  "Check that the clj-fileutils.fileutils/remove-temporary-directory function definition exists."
  (testing "if the clj-fileutils.fileutils/remove-temporary-directory function definition exists."
           (is (callable? 'clj-fileutils.fileutils/remove-temporary-directory))))

(deftest test-remove-directory-existence
  "Check that the clj-fileutils.fileutils/remove-directory function definition exists."
  (testing "if the clj-fileutils.fileutils/remove-directory function definition exists."
           (is (callable? 'clj-fileutils.fileutils/remove-directory))))

(deftest test-mv-file-existence
  "Check that the clj-fileutils.fileutils/mv-file function definition exists."
  (testing "if the clj-fileutils.fileutils/mv-file function definition exists."
           (is (callable? 'clj-fileutils.fileutils/mv-file))))

(deftest test-move-file-existence
  "Check that the clj-fileutils.fileutils/move-file function definition exists."
  (testing
    "if the clj-fileutils.fileutils/move-file function definition exists."
    (is (callable? 'clj-fileutils.fileutils/move-file))))

(deftest test-make-temporary-log-file-name-existence
  "Check that the clj-fileutils.fileutils/make-temporary-log-file-name function definition exists."
  (testing
    "if the clj-fileutils.fileutils/make-temporary-log-file-name function definition exists."
    (is (callable? 'clj-fileutils.fileutils/make-temporary-log-file-name))))

(deftest test-make-temporary-directory-existence
  "Check that the clj-fileutils.fileutils/make-temporary-directory function definition exists."
  (testing
    "if the clj-fileutils.fileutils/make-temporary-directory function definition exists."
    (is (callable? 'clj-fileutils.fileutils/make-temporary-directory))))

(deftest test-filename-list-existence
  "Check that the clj-fileutils.fileutils/filename-list function definition exists."
  (testing "if the clj-fileutils.fileutils/filename-list function definition exists."
           (is (callable? 'clj-fileutils.fileutils/filename-list))))

(deftest test-file-list-existence
  "Check that the clj-fileutils.fileutils/file-list function definition exists."
  (testing "if the clj-fileutils.fileutils/file-list function definition exists."
           (is (callable? 'clj-fileutils.fileutils/file-list))))

;
; Actual tests.
;

(def msg-start
  "Warning: cannot read content of the following file: ")

(deftest test-new-file-1
  "Check the function clj-fileutils.fileutils/new-file."
  (testing "Check the function clj-fileutils.fileutils/new-file."
           (are [x y] (= x y)
                ; new-file just constructs new instance of File object
                ; variant with just filename, not a directory
                ""            (.toString (new-file ""))
                "abc"         (.toString (new-file "abc"))
                "abc.def"     (.toString (new-file "abc.def"))
                "abc/def"     (.toString (new-file "abc/def"))
                "abc/def/xyz" (.toString (new-file "abc/def/xyz")))))

(deftest test-new-file-2
  "Check the function clj-fileutils.fileutils/new-file."
  (testing "Check the function clj-fileutils.fileutils/new-file."
           (are [x y] (= x y)
                ; new-file just constructs new instance of File object
                ; now directory is not empty, but filename is
                "/"           (.toString (new-file "" ""))
                "abc"         (.toString (new-file "abc" ""))
                "abc.def"     (.toString (new-file "abc.def" ""))
                "abc/def"     (.toString (new-file "abc/def" ""))
                "abc/def/xyz" (.toString (new-file "abc/def/xyz" "")))))

(deftest test-new-file-3
  "Check the function clj-fileutils.fileutils/new-file."
  (testing "Check the function clj-fileutils.fileutils/new-file."
           (are [x y] (= x y)
                ; new-file just constructs new instance of File object
                ; now directory is empty
                "/"            (.toString (new-file "" ""))
                "/abc"         (.toString (new-file "" "abc"))
                "/abc.def"     (.toString (new-file "" "abc.def"))
                "/abc/def"     (.toString (new-file "" "abc/def"))
                "/abc/def/xyz" (.toString (new-file "" "abc/def/xyz")))))

(deftest test-new-file-4
  "Check the function clj-fileutils.fileutils/new-file."
  (testing "Check the function clj-fileutils.fileutils/new-file."
           (are [x y] (= x y)
                ; new-file just constructs new instance of File object
                ; both directory and filename are not empty
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
  (testing
    "Check the function clj-fileutils.fileutils/make-temporary-directory."
    (let [tempdir (make-temporary-directory)]
      (println "Temporary directory" tempdir)
      (is (not (nil? tempdir)))
      (is (.startsWith (.getPath tempdir) "/tmp"))
      ; perform regular cleanup after previous block is executed
      (if tempdir
        (remove-temporary-directory (.getPath tempdir))))))

(deftest test-make-temporary-directory-2
  "Check the function clj-fileutils.fileutils/make-temporary-directory."
  (testing
    "Check the function clj-fileutils.fileutils/make-temporary-directory."
    (let [tempdir (make-temporary-directory)]
      (println "Temporary directory" tempdir)
      (is (.isDirectory tempdir))
      (is (not (.isFile tempdir)))
      ; perform regular cleanup after previous block is executed
      (if tempdir
        (remove-temporary-directory (.getPath tempdir))))))

(deftest test-make-temporary-log-file-name-1
  "Check the function clj-fileutils.fileutils/make-temporary-log-file-name."
  (testing
    "Check the function clj-fileutils.fileutils/make-temporary-log-file-name."
    (let [templog (make-temporary-log-file-name)]
      (println "Temporary log file" templog)
      (is (not (nil? templog)))
      (is (.startsWith templog "/tmp"))
      ; perform regular cleanup after previous block is executed
      (if templog
        (-> (new java.io.File templog)
            .delete)))))

(deftest test-make-temporary-log-file-name-2
  "Check the function clj-fileutils.fileutils/make-temporary-log-file-name."
  (testing
    "Check the function clj-fileutils.fileutils/make-temporary-log-file-name."
    (let [templog (make-temporary-log-file-name)]
      (println "Temporary log file" templog)
      (let [f (new java.io.File templog)]
        (is (not (.isDirectory f)))
        (is (.isFile f))
        ; perform regular cleanup after previous block is executed
        (if templog
          (.delete f))))))

(deftest test-remove-directory
  "Check the function clj-fileutils.fileutils/remove-directory."
  (testing "Check the function clj-fileutils.fileutils/remove-directory."
           (let [tempdir (make-temporary-directory)]
             (println "Temporary directory to be deleted" tempdir)
             (when (.isDirectory tempdir)
               (is (.isDirectory tempdir))
               (remove-directory tempdir)
               (is (not (.isDirectory tempdir)))))))

(deftest test-print-slurp-exception
  "Check the function clj-fileutils.fileutils/print-slurp-exception."
  (testing
    "the function clj-fileutils.fileutils/print-slurp-exception."
    (is (= (with-out-str (print-slurp-exception "test.txt"))
           "Warning: cannot read content of the following file: test.txt\n"))
    (is (= (with-out-str (print-slurp-exception "testdir" "test.txt"))
           "Warning: cannot read content of the following file: testdir/test.txt\n"))))

(deftest test-slurp-
  "Check the function clj-fileutils.fileutils/slurp-."
  (testing "Check the function clj-fileutils.fileutils/slurp-."
           (are [expected filename] (= expected (slurp- filename))
                ""           "test/test1.txt"
                "test\n"     "test/test2.txt"
                "1\n2\n3\n"  "test/test3.txt")))

(deftest test-slurp-2
  "Check the function clj-fileutils.fileutils/slurp-."
  (testing "Check the function clj-fileutils.fileutils/slurp-."
           (are [expected directory filename]
                (= expected (slurp- directory filename))
                ""           "test" "test1.txt"
                "test\n"     "test" "test2.txt"
                "1\n2\n3\n"  "test" "test3.txt")))

(deftest test-slurp-file-not-found
  "Check the function clj-fileutils.fileutils/slurp-."
  (testing "Check the function clj-fileutils.fileutils/slurp-."
           (is (nil? (slurp- "unknown.txt")))
           (is (nil? (slurp- "directory" "unknown.txt")))
           (is (nil? (slurp- "test" "unknown.txt")))))

(deftest test-move-file
  "Check the function clj-fileutils.fileutils/move-file."
  (testing "Check the function clj-fileutils.fileutils/move-file"
           (spit "test_file1" "test")
           (is (.isFile (new-file "test_file1")))
           (is (not (.isFile (new-file "test_file2"))))
           (move-file "test_file1" "test_file2")
           (is (not (.isFile (new-file "test_file1"))))
           (is (.isFile (new-file "test_file2")))
           ; perform regular cleanup after previous block is executed
           (.delete (new-file "test_file1"))
           (.delete (new-file "test_file2"))))

(deftest test-filename-list
  "Check the function clj-fileutils.fileutils/filename-list"
  (testing "Check the function clj-fileutils.fileutils/filename-list"
           (let [test-files (filename-list "test")]
             (is (some #{"test1.txt"} test-files))
             (is (some #{"test2.txt"} test-files))
             (is (some #{"test3.txt"} test-files))
             (is (not (some #{"foobarbaz"} test-files))))))

(deftest test-filename-list-2
  "Check the function clj-fileutils.fileutils/filename-list"
  (testing "Check the function clj-fileutils.fileutils/filename-list"
           (let [test-files (filename-list "test" ".txt")]
             (is (some #{"test1.txt"} test-files))
             (is (some #{"test2.txt"} test-files))
             (is (some #{"test3.txt"} test-files))
             (is (not (some #{"foobarbaz"} test-files))))))

(deftest test-filename-list-3
  "Check the function clj-fileutils.fileutils/filename-list"
  (testing "Check the function clj-fileutils.fileutils/filename-list"
           (let [test-files (filename-list "test" ".baz")]
             (is (not (some #{"test1.txt"} test-files)))
             (is (not (some #{"test2.txt"} test-files)))
             (is (not (some #{"test3.txt"} test-files)))
             (is (not (some #{"foobarbaz"} test-files))))))

(deftest test-file-list
  "Check the function clj-fileutils.fileutils/file-list"
  (testing "Check the function clj-fileutils.fileutils/file-list"
           (let [test-files (file-list "test")
                 filenames (map #(.getName %) test-files)]
             (is (some #{"test1.txt"} filenames))
             (is (some #{"test2.txt"} filenames))
             (is (some #{"test3.txt"} filenames))
             (is (not (some #{"foobarbaz"} filenames))))))

(deftest test-file-list-2
  "Check the function clj-fileutils.fileutils/filename-list"
  (testing "Check the function clj-fileutils.fileutils/filename-list"
           (let [test-files (file-list "test" ".txt")
                 filenames (map #(.getName %) test-files)]
             (is (some #{"test1.txt"} filenames))
             (is (some #{"test2.txt"} filenames))
             (is (some #{"test3.txt"} filenames))
             (is (not (some #{"foobarbaz"} filenames))))))

(deftest test-file-list-3
  "Check the function clj-fileutils.fileutils/filename-list"
  (testing "Check the function clj-fileutils.fileutils/filename-list"
           (let [test-files (file-list "test" ".baz")
                 filenames (map #(.getName %) test-files)]
             (is (not (some #{"test1.txt"} filenames)))
             (is (not (some #{"test2.txt"} filenames)))
             (is (not (some #{"test3.txt"} filenames)))
             (is (not (some #{"foobarbaz"} filenames))))))

