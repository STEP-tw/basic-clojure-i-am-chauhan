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