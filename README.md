# Tutorial - Adpro C
## Fikri Dhiya Ramadhana - 2206819533
## Advanced Programming C / MSG

Link to [eshop](https://adpro-tutorial-fikrirmdhna-adpro.koyeb.app/product/list) app

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-orange.svg)](https://sonarcloud.io/summary/new_code?id=fikrirmdhna_tutorial-2)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=fikrirmdhna_tutorial-2&metric=coverage)](https://sonarcloud.io/summary/new_code?id=fikrirmdhna_tutorial-2) [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=fikrirmdhna_tutorial-2&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=fikrirmdhna_tutorial-2) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=fikrirmdhna_tutorial-2&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=fikrirmdhna_tutorial-2)


## Tutorial 1
<details>
<summary>Expand</summary>

### Reflection 1

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
</details>
</br>

## Tutorial 2
### Reflection
1.  List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

* Strategi saya dalam memperbaiki *quality issue(s)* adalah menghilangkan `return null` dari kode productRepository.findProductById yang saya buat karena akan membuat missing branches sehingga harus ditekankan dengan membuat instance produk terlebih dahulu.
* Menghilangkan if-conditional yang tidak perlu untuk memastikan bahwa skenario akan selalu terpenuhi.
* Membuat unit test untuk masing - masing folder agar code coverage terpenuhi dan melakukan assertion test dan verify test untuk menguji apakah test yang dibuat menghasilkan output yang diinginkan.

2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)! 
* Menurut saya, CI/CD yang sudah saya implementasikan telah memenuhi konsep yang ada sebagai contoh penggunaan GitHub Workflows sebagai automisasi *integration* dan *deployment* saat kita melakukan perubahan *push* atau *pull*. Saat melakukan perubahan perubahan pada branch, kode akan diuji yang ada di ci.yml, scorecard.yml, dan sonarcloud.yml. Dan ketika ketiga pengecekan tersebut berhasil, kode dapat digabungkan ke main sehingga dapat dideploy dengan PaaS Koyeb dan diperiksa keamanan kode dengan scorecard.yml yang dimana akan terbentuk suatu siklus *software development life*. 