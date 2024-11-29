Kapsam:

Bir e-ticaret platformu için sipariş yönetim sisteminin bir parçası olarak sipariş işleme ve fatura oluşturma modülü tasarlanacaktır. Siparişler farklı ürünler içerebilir ve her ürünün fiyatı, vergisi ve indirimi olabilir. Sipariş tamamlandıktan sonra, sistem fatura oluşturmalı ve bu faturayı veritabanına kaydetmelidir.

Gereksinimler:

1.⁠ ⁠Sipariş Modeli:

- Siparişlerin birden fazla ürünü içerebilmesi gerekmektedir.
- Her ürünün adı, fiyatı, vergisi ve indirimi olmalıdır.
- Toplam sipariş tutarı, ürünlerin vergileri ve indirimleri hesaplandıktan sonra elde edilmelidir.

2.⁠ ⁠Fatura İşlemleri:

- Siparişin işlenmesinden sonra bir fatura oluşturulmalıdır. (Opsiyonel)
- Fatura, siparişin detaylarını, toplam tutarı ve tarihi içermelidir.(Opsiyonel)
- Fatura, ilişkisel bir veritabanına kaydedilmelidir.(Opsiyonel)
  •⁠ ⁠Opsiyonel kısımlar ek geliştirme olmadan mock bir servisle de sağlanabilir

3.⁠ ⁠Dayanıklılık ve Hata Yönetimi:

- Sipariş kaydedilmeden önce, tüm ürünlerin mevcut olup olmadığı kontrol edilmelidir.
- Veritabanı işlemlerinde başarısızlık durumunda, hata yakalanmalı ve sistem belirli bir geri alma (rollback) mekanizması kullanarak sipariş işlemini geri almalıdır.
- Hata durumunda anlamlı hata mesajları üretilmelidir.

4.⁠ ⁠Performans ve Ölçeklenebilirlik (Opsiyonel):

- Aynı anda birden fazla sipariş işlenebilmeli.
- Sistem, veri bütünlüğünü koruyarak yüksek trafikte de sorunsuz çalışmalıdır.

Ek Bilgiler:

- Sistem mikroservis mimarisi ile tasarlanmışsa, httpClient ile fatura mikroservisine bir HTTP isteği gönderilebilir.
- Sipariş işlemi için bir mesaj kuyruğu (örneğin Kafka) kullanılabilir ve fatura oluşturma işlemi asenkron olarak gerçekleştirilebilir.
  •⁠ ⁠Fatura servisi geliştirmek opsiyoneldir. Bu servisi bir mock yapısı kullanılabilir
  •⁠ ⁠Bütün bağımlılıklar docker-compose ile ayağa kaldırılabilir durumda olmalıdır
  •⁠ ⁠Teknoloji seçimi serbesttir

Sorular:
1.⁠ ⁠Bu sistemi nasıl tasarlarsınız? Kullanacağınız sınıflar, arayüzler ve modeller nelerdir?
2.⁠ ⁠Veritabanı işlemlerini nasıl ele alırsınız? Hangi veri modeli ve tablo yapısını tercih edersiniz?
3.⁠ ⁠Fatura işlemi sırasında veritabanı hataları meydana gelirse, bu durumu nasıl yönetirsiniz?
4.⁠ ⁠Aynı anda birden fazla siparişin işlenmesini nasıl ele alırsınız? Concurrency (eşzamanlılık) yönetimi için hangi Java özelliklerini veya araçlarını kullanırsınız?
5.⁠ ⁠Bu uygulamanın test edilebilirliğini nasıl sağlarsınız? Hangi test stratejilerini kullanırsınız (ünite testi, entegrasyon testi vb.)?
6.⁠ ⁠Performans ve ölçeklenebilirlik açısından bu uygulamayı nasıl optimize edersiniz?
