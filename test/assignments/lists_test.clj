(ns assignments.lists-test
  (:require [clojure.test :refer :all]
            [assignments.lists :refer :all]))

(deftest filter-test
  (testing "even? predicate"
    (is (= [2 4 6] (filter' even? [1 2 3 4 5 6 9]))))
  (testing "filter not nil values"
    (is (= [1 2 4] (filter' (comp not nil?) [1 nil 2 nil nil 4])))))

(deftest reduce'-test
  (testing "without initial value"
    (is (= 10 (reduce' + [1 2 3 4]))))
  (testing "with initial value"
    (is (= 4 (reduce - 10 [1 2 3])))))

(deftest count'-test
  (testing "with non-empty collection"
    (is (= 4 (count' [1 2 3 4]))))
  (testing "with empty collection"
    (is (= 0 (count' []))))
  (testing "with nested collection"
    (is (= 5 (count' [[1] [1 2] [] [1 2 3 4] [5]])))))

(deftest reverse-test
  (testing "with empty list"
    (is (= () (reverse' []))))
  (testing "with elements in list"
    (is (= '(3 2 1) (reverse' [1 2 3]))))
  (testing "with string"
    (is (= '(\e \r \u \j \o \l \c) (reverse' "clojure"))))
  (testing "with non-seqable"
    (is (nil? (reverse' true)))))

(deftest every?-test
  (testing "with empty list"
    (is (true? (every?' odd? []))))
  (testing "odd? with odd value"
    (is (true? (every?' odd? [1 3 5]))))
  (testing "odd? with one even value in list"
    (is (false? (every?' odd? [1 2 3])))))

(deftest some?-test
  (testing "with empty list"
    (is (false? (some?' odd? []))))
  (testing "odd? with odd value"
    (is (true? (some?' odd? [1 3 5]))))
  (testing "odd? with one even value in list"
    (is (true? (some?' odd? [1 2 3]))))
  (testing "even? with no even values in list"
    (is (false? (some?' even? [1 3 5])))))

(deftest ascending?-test
  (testing "with correct sequence"
    (is (true? (ascending? [1 2 3]))))
  (testing "with wrong sequence"
    (is (false? (ascending? [1 4 3])))))

(deftest sqr-of-the-first-test
  (testing "with single item"
    (is (= [1] (sqr-of-the-first [1]))))
  (testing "with collection containing 4 items"
    (is (= [4 4 4 4] (sqr-of-the-first [2 3 4 5])))))

(deftest difference-test
  (testing "non-empty collection"
    (is (= [5 6 7 8] (difference [1 2 3 4] [5 6 1 2 7 8])))))

(deftest russian-dolls-test
  (testing "single nesting gives result as same input"
    (is (= [1 2 3] (russian-dolls [1 2 3] 1))))
  (testing "triple nesting"
    (is (= [[[1]] [[2]] [[3]]] (russian-dolls [1 2 3] 3)))))

(deftest distinct-test
  (testing "with no duplicates"
    (is (= [1 2 3] (distinct' [1 2 3]))))
  (testing "with duplicates"
    (is (= [1 2 3] (distinct' [1 1 2 3 2])))))

(deftest dedupe-test
  (testing "with no duplicates"
    (is (= [1 2 3] (dedupe' [1 2 3]))))
  (testing "with duplicates but not consecutive"
    (is (= [1 2 3 1] (dedupe' [1 2 3 1]))))
  (testing "with consecutive duplicates"
    (is (= [1 2 3] (dedupe' [1 1 2 3 3])))))

(deftest transpose-test
  (testing "non-empty collection"
    (is (= [[:a :d] [:b :e] [:c :f]] (transpose [[:a :b :c] [:d :e :f]])))
    (is (= [[:a :b] [:c :d]] (transpose [[:a :c] [:b :d]])))))

(deftest third-or-fifth-test
  (testing "non-empty collection"
    (is (= [1 4 6 7] (third-or-fifth [1 2 3 4 5 6 7 8 9])))))