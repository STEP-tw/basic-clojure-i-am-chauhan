(ns assignments.lists-test
  (:require [clojure.test :refer :all]
            [assignments.lists :refer :all]))

(deftest map-test
  (testing "identity with single coll"
    (is (= [1 2 3] (map' identity [1 2 3]))))
  (testing "addition with multiple colls of same size"
    (is (= [5 7 9] (map' + [1 2 3] [4 5 6]))))
  (testing "addition with multiple colls of different sizes"
    (is (= [5 7] (map' + [1 2 3] [4 5])))))

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
    (is (= [[:a :d] [:b :e] [:c :f]] (transpose [[:a :b :c] [:d :e :f]])))))

(deftest third-or-fifth-test
  (testing "non-empty collection"
    (is (= [1 4 6 7] (third-or-fifth [1 2 3 4 5 6 7 8 9])))))

(deftest double-up-test
  (testing "single dimensional collection"
    (is (= [1 1 2 2 3 3] (double-up [1 2 3]))))
  (testing "two dimensional collection"
    (is (= [[1 2] [1 2] [3 4] [3 4]] (double-up [[1 2] [3 4]])))))

(deftest cross-product-test
  (testing "non-empty collection"
    (is (= [[1 4] [1 3] [1 5] [2 4] [2 3] [2 5] [3 4]] (cross-product [1 2 3] [4 3 5])))))

(deftest union-test
  (testing "non-empty collection"
    (is (= [1 2 3 4 5 6 7 8] (union [1 2 3 4] [5 6 1 2 7 8])))))

(deftest sum-of-adjacent-digits-test
  (testing "non-empty collection"
    (is (= [3 5 7] (sum-of-adjacent-digits [1 2 3 4])))))

(deftest muted-thirds-test
  (testing "non-empty collection"
    (is (= [1 2 0 4 5 0 7 8] (muted-thirds [1 2 3 4 5 6 7 8])))))

(deftest split-comb-test
  (testing "odd length collection"
    (is (= '(1 3 2 4 5) (split-comb [1 2 3 4 5]))))
  (testing "even length collection"
    (is (= '(1 4 2 5 3 6) (split-comb [1 2 3 4 5 6])))))

(deftest palindrome?-test
  (testing "palindrome collection"
    (is (= true (palindrome? [1 2 3 2 1]))))
  (testing "not a palindrome collection"
    (is (= false (palindrome? [1 2 4 3 1])))))

(deftest index-of-test
  (testing "with empty collection"
    (is (= -1 (index-of [] "something"))))
  (testing "with element present in the collection"
    (is (= 4 (index-of [0 1 2 5 3] 3))))
  (testing "with element not present in the collection"
    (is (= -1 (index-of [1 2 3] 0)))))

(deftest max-three-digit-sequence-test
  (testing "with less than three numbers"
    (is (= [1 2] (max-three-digit-sequence [1 2]))))
  (testing "with more than three numbers"
    (is (= [2 -1 2] (max-three-digit-sequence [1 2 -1 2 0])))))

(deftest points-around-origin-test
  (testing "points around origin"
    (is (= '([-1 -1]
             [-1 0]
             [-1 1]
             [0 -1]
             [0 1]
             [1 -1]
             [1 0]
             [1 1])
           points-around-origin))))

(deftest validate-sudoku-grid-test
  (testing "correct grid"
    (is (true? (validate-sudoku-grid
                 [[4 3 5 2 6 9 7 8 1]
                  [6 8 2 5 7 1 4 9 3]
                  [1 9 7 8 3 4 5 6 2]
                  [8 2 6 1 9 5 3 4 7]
                  [3 7 4 6 8 2 9 1 5]
                  [9 5 1 7 4 3 6 2 8]
                  [5 1 9 3 2 6 8 7 4]
                  [2 4 8 9 5 7 1 3 6]
                  [7 6 3 4 1 8 2 5 9]]))))
  (testing "incorrect grid"
    (is (false? (validate-sudoku-grid
                  [[4 4 5 2 6 9 7 8 1]
                   [6 8 2 5 7 1 4 9 3]
                   [1 9 7 8 3 4 5 6 2]
                   [8 2 6 1 9 5 3 4 7]
                   [3 7 4 6 8 2 9 1 5]
                   [9 5 1 7 4 3 6 2 8]
                   [5 1 9 3 2 6 8 7 4]
                   [2 4 8 9 5 7 1 3 6]
                   [7 6 3 4 1 8 2 5 9]])))))