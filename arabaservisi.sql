-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 15 May 2023, 15:06:39
-- Sunucu sürümü: 10.4.28-MariaDB
-- PHP Sürümü: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `arabaservisi`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `arababilgileri`
--

CREATE TABLE `arababilgileri` (
  `araba_id` int(11) NOT NULL,
  `kullanici_id` int(11) NOT NULL,
  `marka` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `yil` varchar(20) NOT NULL,
  `plaka` varchar(15) NOT NULL,
  `kilometre` int(11) NOT NULL,
  `yakit` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `arababilgileri`
--

INSERT INTO `arababilgileri` (`araba_id`, `kullanici_id`, `marka`, `model`, `yil`, `plaka`, `kilometre`, `yakit`) VALUES
(2, 2, 'honda', 'civic', '2022', '34 SDF 654', 3854, 'Hybrid'),
(6, 3, 'fiat', 'doblo', '2009', '34 DAP 7452', 150000, 'Dizel'),
(7, 2, 'Renault', 'Megane', '2015', '34 TFY 23', 45223, 'Elektrik'),
(160, 16, 'Kia', 'Picanto', '2003', '42 DS 231', 2332, 'Benzin'),
(161, 14, 'Peugeot', '2008', '2023', '34ADH3650', 165023, 'Benzin'),
(162, 18, 'Kia', 'Picanto', '2003', '4502', 420, 'Benzin'),
(163, 0, 'Toyota', 'Corolla', '2021', '34 ASD 582', 1502, 'Hybrid');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `iletisimbilgileri`
--

CREATE TABLE `iletisimbilgileri` (
  `iletisim_id` int(11) NOT NULL,
  `adres` text NOT NULL,
  `telefon` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `iletisimbilgileri`
--

INSERT INTO `iletisimbilgileri` (`iletisim_id`, `adres`, `telefon`) VALUES
(1, 'Hürriyet Mah. Yakacık D-100 E-5 Kuzey Yanyol Cd. No:45 Kartal/ İstanbul', '0232-354-41-25'),
(2, 'İstasyon, 1458. Sk No:1, 41400 Gebze/Kocaeli', '0216-358-24-12'),
(3, 'Sanayi Mh İzmit Sanayi Sitesi 1. Blok D:17', '0262-189-21-84');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kullanicibilgileri`
--

CREATE TABLE `kullanicibilgileri` (
  `id` int(11) NOT NULL,
  `kullaniciAd` text NOT NULL,
  `kullaniciSoyad` text NOT NULL,
  `sifre` text NOT NULL,
  `email` varchar(200) NOT NULL,
  `reset_password_otp` text NOT NULL,
  `reset_password_created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `kullanicibilgileri`
--

INSERT INTO `kullanicibilgileri` (`id`, `kullaniciAd`, `kullaniciSoyad`, `sifre`, `email`, `reset_password_otp`, `reset_password_created_at`) VALUES
(0, 'admin', 'admin', '$2y$10$aQJgyUaJu/yTNJ5m/cCrduI1g4Qxugy1.vhf34tC/O6IbrF72E2j.', 'admin', '', '2023-05-12 21:09:56'),
(2, 'mustafa', 'ince', '$2y$10$0j/zqDKl5T/gJ15mGjWvAuAu2LeOgEAf5DSbp4T35wfVJzq4htGT2', 'mustafa@gmail.com', '', '2023-05-15 12:29:44'),
(3, 'mehdi', 'can', '$2y$10$SOrf5BBMdxc7geim7IS5buYk/28F0xr8ukAOgDiqQGEPzXf.kv1AK', 'mehdican@gmail.com', '', '2023-05-15 12:29:25'),
(14, 'alper', 'ercun', '$2y$10$YOjhrN0JVZgISYBdajVooO2lyWuulzxgrXVYMiKbz4wh1aJgdjmdW', 'alpersargin@gmail.com', '', '2023-05-12 21:09:56'),
(16, 'ahmet', 'kaya', '$2y$10$7Vs3wsBk9.gKmhFopeLRG.IBuyRhiKnAGiX2Zy862PvTEZOW6PK5G', 'ahmetkaya@gmail.com', '', '2023-05-12 21:09:56'),
(18, 'ahmet', 'yılmaz', '$2y$10$XJwtRtzCbNnKfohhQJnXG.fOL4hskacCnpKRnFtcz3YeOumFNOrvK', 'ahmet@gmail.com', '', '2023-05-14 09:17:28'),
(20, 'mehdi can', 'akbaba', '$2y$10$u6wmnJFIO3NklslzXF0UMOhvrh1I.3hwN2eT0j4uRATfashn2vYVe', 'mehdi.can.akbaba@gmail.com', '', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `servisbilgileri`
--

CREATE TABLE `servisbilgileri` (
  `servis_id` int(11) NOT NULL,
  `kullanici_id` int(11) NOT NULL,
  `hizmetTuru` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `servisbilgileri`
--

INSERT INTO `servisbilgileri` (`servis_id`, `kullanici_id`, `hizmetTuru`) VALUES
(186, 18, 'Periyodik Bakım'),
(187, 14, 'Periyodik Bakım'),
(188, 3, 'Onarım Hizmetleri'),
(189, 16, 'Boyama ve Kaporta'),
(190, 0, 'Onarım Hizmetleri');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `urunler`
--

CREATE TABLE `urunler` (
  `urun_id` int(11) NOT NULL,
  `urun_ad` varchar(45) NOT NULL,
  `fiyat` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `urunler`
--

INSERT INTO `urunler` (`urun_id`, `urun_ad`, `fiyat`) VALUES
(1, 'Mikrofiber Bezler', '89.90 ₺'),
(2, 'Araç Bakım Setleri', '475 ₺'),
(3, 'Oto Kriko (1.7 Ton)', '299.90 ₺'),
(4, 'Araç İçi Yangın Tüpleri (1kg)', '189.90 ₺'),
(5, 'Ultra 10W-40 Motor Yağı', '214.99 ₺');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `arababilgileri`
--
ALTER TABLE `arababilgileri`
  ADD PRIMARY KEY (`araba_id`),
  ADD UNIQUE KEY `plaka` (`plaka`),
  ADD KEY `arabakullanici_id` (`kullanici_id`);

--
-- Tablo için indeksler `iletisimbilgileri`
--
ALTER TABLE `iletisimbilgileri`
  ADD PRIMARY KEY (`iletisim_id`);

--
-- Tablo için indeksler `kullanicibilgileri`
--
ALTER TABLE `kullanicibilgileri`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Tablo için indeksler `servisbilgileri`
--
ALTER TABLE `servisbilgileri`
  ADD PRIMARY KEY (`servis_id`),
  ADD KEY `serviskullanici_id` (`kullanici_id`);

--
-- Tablo için indeksler `urunler`
--
ALTER TABLE `urunler`
  ADD PRIMARY KEY (`urun_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `arababilgileri`
--
ALTER TABLE `arababilgileri`
  MODIFY `araba_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=164;

--
-- Tablo için AUTO_INCREMENT değeri `iletisimbilgileri`
--
ALTER TABLE `iletisimbilgileri`
  MODIFY `iletisim_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `kullanicibilgileri`
--
ALTER TABLE `kullanicibilgileri`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Tablo için AUTO_INCREMENT değeri `servisbilgileri`
--
ALTER TABLE `servisbilgileri`
  MODIFY `servis_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=192;

--
-- Tablo için AUTO_INCREMENT değeri `urunler`
--
ALTER TABLE `urunler`
  MODIFY `urun_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `arababilgileri`
--
ALTER TABLE `arababilgileri`
  ADD CONSTRAINT `arabakullanici_id` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanicibilgileri` (`id`);

--
-- Tablo kısıtlamaları `servisbilgileri`
--
ALTER TABLE `servisbilgileri`
  ADD CONSTRAINT `serviskullanici_id` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanicibilgileri` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
