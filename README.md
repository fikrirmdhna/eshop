# Tutorial 1 - Adpro C
## Fikri Dhiya Ramadhana - 2206819533

## Reflection 1
### Clean Code
Dalam tutorial ini, *clean code* yang sudah saya terapkan adalah: 
1. Penamaan variabel, function, dan lain - lain yang sudah jelas dan tidak ambigu.
2. Pembuatan Function untuk mempersingkat baris kode atau meminimalisir penulisan kode yang sama. 
3. Terdapat dokumentasi kode testing apa yang akan dilakukan di [sini](src/test/java/id/ac/ui/cs/advprog/eshop/repository/ProductRepositoryTest.java).
4. Mengusahakan kode yang sudah saya buat *readable* dan memiliki indentasi yang konsisten.

### Secure Code
Dalam tutorial ini, *secure coding* yang sudah saya terapkan adalah: 
1. Sudah menggunakan input validation di [sini](src/main/java/id/ac/ui/cs/advprog/eshop/controller/ProductController.java) untuk mengambil *productId* dari *PathVariable* untuk menghindari *injection attack*. 
2. *Error handling* di dalam ProductController agar *product* yang akan diedit tidak bertipe null dan sesuai dengan *product* yang akan diedit.
3. Mengimplementasikan *access control*, seperti *GET* dan *POST* method.

## Reflection 2
1. Setelah saya membuat unit test di dalam proyek ini, saya merasa tenang karena kode yang saya buat sudah berjalan seperti apa yang saya inginkan.Banyaknya test yang ada di dalam suatu class dapat bervariasi tergantung dari code behavior yang akan kita test dan mencegah hal yang tidak diinginkan saat menjalankan kode kita tersebut. Jika kode saya sudah 100% code coverage belum tentu kode saya tidak memiliki bug atau error karena unit test sendiri memiliki limitasi. 

2. Jika diminta untuk membuat java class baru dengan setup dan variabel yang sama seperti CreateProductFunctionalTest, hal ini akan membuat kerapihan kode berkurang karena terdapat setup dan variabel yang sama dan seharusnya tidak perlu ditulis kembali. Hanya perlu digunakan kembali untuk membuat test case yang sesuai permintaan.