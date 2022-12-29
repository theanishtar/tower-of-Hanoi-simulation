[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](http://tranhuudang.cf)
<br/>

<br/>


# Tên dự án: Phần mềm mô phỏng bài toán giải Tháp Hà Nội bằng đệ quy và AKT

<br/>

## Demo


<br>

>Màn hình ứng dụng khi chạy mô phỏng

<img src="https://raw.githubusercontent.com/echhoclaptrinh/Image/main/tower-of-Hanoi-simulasion/simulation.gif" title="" alt="" >

<br>

<br>

<br>

## Documents
- [Cài đặt và sử dụng](#install)
- [Giao diện](#ui)
- [Mô phỏng](#demo)
- [phương thức](#method)

<br>

<hr>

## UI


<br>



>Màn hình ứng dụng khi chạy mô phỏng

<img src="https://raw.githubusercontent.com/echhoclaptrinh/Image/main/tower-of-Hanoi-simulasion/simulation.gif" title="" alt="none" >

<br>

<br>

<br>

>Màn hình ứng dụng cửa sổ chính

<img src="https://raw.githubusercontent.com/echhoclaptrinh/Image/main/tower-of-Hanoi-simulasion/main.png" title="" alt="none" >

<br>

<br>

<br>

>Màn hình trang thông tin

<img src="https://raw.githubusercontent.com/echhoclaptrinh/Image/main/tower-of-Hanoi-simulasion/infor.png" title="" alt="none" >

<br>

<br>

<br>

>Màn hình ứng dụng khi hoàn tất mô phỏng

<img src="https://raw.githubusercontent.com/echhoclaptrinh/Image/main/tower-of-Hanoi-simulasion/done.png" title="" alt="none" >

<br>

<br>

<br>

## Install

1. Clone repo về máy
2. Mở bằng Netbeans IDE
3. Chạy lớp "com.gui.MainForm"

<br/>

<br/>

## Method

>Code Java - Hanoi Tower by Recursion

<br>

```java
package com.code.demo;

import java.util.Scanner;

/**
 * @author Dang Tran Huu
 */
public class towerOfHanoi {

    static Scanner inp = new Scanner(System.in);
    
    void shift(int n, char a, char b) {
        System.out.printf("Chuyen dia thu %d tu coc %c sang coc %c\n", n, a, b);
    }

    void towerHanoi(int n, char a, char b, char c) {
        if (n == 1) {
            shift(1, a, c);
        } else {
            towerHanoi(n - 1, a, c, b);
            shift(n, a, c);
            towerHanoi(n - 1, b, a, c);
        }
    }

    public static void main(String[] args) {
        towerOfHanoi al = new towerOfHanoi();
        int n;
        char a = 'A', b = 'B', c = 'C';
        n = inp.nextInt();
        al.towerHanoi(n,a,b,c);
    }
}

```
<br/>

>Code C++ - Hanoi Tower by Recursion

<br>

```cpp
#include<iostream>
using namespace std;

void shift(int n, char a, char b){
	cout<<"\n Chuyen dia thu "<< n << " tu coc " << a << " sang coc " << b;
}

void towerHanoi(int n, char a, char b, char c){
	if (n==1)
		shift(1,a,c);
	else {
		towerHanoi(n-1,a,c,b);
		shift(n,a,c);
		towerHanoi(n-1,b,a,c);
	}
}

int main(){
	int n;
	char a = 'A', b = 'B', c = 'C';
	
	cout << "Nhap so dia N = ";
	cin>> n;
	
	towerHanoi(n,a,b,c);
	
	return 0;
}

```

<br>

[Designed by Theanishtar in CanTho city.](http://tranhuudang.cf)
