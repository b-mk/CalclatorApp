# 電卓アプリ

## プロジェクト名

CalculatorApp

## 詳細

授業で制作しました。AWTコンポーネントを用いた、四則演算が行えるアプリケーションです。

## 使い方

1. 'CalclatorApp\Java'上でコマンドプロンプトを開き、`javac CalculatorApp.java`を入力する
2. 'java CalculatorApp'を入力すると実行できる

## 製作者

mi161332@edu.okinawa-ct.ac.jp

## 工夫したところ

小数の計算に対応したこと。
また、安全設計手法を心掛けたこと。たとえば
- 0除算を行ったり計算結果がエラーとなってしまった場合、Cボタン以外を押しても反応しない
- 連続で演算子がクリックされたり、=ボタンを押したあと続けて計算を行おうとしたりした場合も、正常に計算ができる

## 最終更新日

2019/8/5