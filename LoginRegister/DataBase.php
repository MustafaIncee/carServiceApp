<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $username, $password)
    {
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $this->sql = "select * from " . $table . " where email = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) !== 0) {
            $dbusername = $row['email'];
            $dbpassword = $row['sifre'];
            if ($dbusername == $username && password_verify($password, $dbpassword)) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $kullaniciAd, $email, $kullaniciSoyad, $password)
    {
        $kullaniciAd = $this->prepareData($kullaniciAd);
        $kullaniciSoyad = $this->prepareData($kullaniciSoyad);
        $password = $this->prepareData($password);
        $email = $this->prepareData($email);
        $password = password_hash($password, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (kullaniciAd, kullaniciSoyad, sifre, email) VALUES ('" . $kullaniciAd . "','" . $kullaniciSoyad . "','" . $password . "','" . $email . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function carInformation($table, $marka, $model, $yil, $plaka, $kilometre,$yakit)
    {
        $marka = $this->prepareData($marka);
        $model = $this->prepareData($model);
        $yil = $this->prepareData($yil);
        $plaka = $this->prepareData($plaka);
        $kilometre = $this->prepareData($kilometre);
        $yakit = $this->prepareData($yakit);
        $this->sql =
            "INSERT INTO " . $table . " (marka, model, yil, plaka, kilometre, yakit) VALUES ('" . $marka . "','" . $model . "','" . $yil . "','" . $plaka . "','" . $kilometre . "','" . $yakit . "')";
            if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }


    function MyCars($table, $araba_id,$kullanici_id,$marka, $model, $yil, $plaka, $kilometre,$yakit)
    {
        $kullanici_id=$this->prepareData($kullanici_id);
        $araba_id=$this->prepareData($araba_id);
        $marka = $this->prepareData($marka);
        $model = $this->prepareData($model);
        $yil = $this->prepareData($yil);
        $plaka = $this->prepareData($plaka);
        $kilometre = $this->prepareData($kilometre);
        $yakit = $this->prepareData($yakit);
        $this->sql =
            "SELECT " . $table . " (marka, model, yil, plaka, kilometre, yakit) VALUES ('" . $marka . "','" . $model . "','" . $yil . "','" . $plaka . "','" . $kilometre . "','" . $yakit . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function aracSil($table, $plaka)
    {
        $plaka = $this->prepareData($plaka);
        $this->sql = "DELETE FROM " . $table . " WHERE plaka = '" . $plaka . "'";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else {
            return false;
        }
    }

    function Services($table, $hizmetTuru)
    {
        $hizmetTuru = $this->prepareData($hizmetTuru);
        $this->sql =
            "INSERT INTO " . $table . " (hizmetTuru) VALUES ('" . $hizmetTuru . "')";
            if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function Addresses($table, $adres, $telefon)
    {
        $adres=$this->prepareData($adres);
        $telefon=$this->prepareData($telefon);

        $this->sql =
            "SELECT " . $table . " (adres,telefon) VALUES ('" . $adres . "','" . $telefon . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function Urunler($table, $urun_ad, $fiyat)
    {
        $urun_ad=$this->prepareData($urun_ad);
        $fiyat=$this->prepareData($fiyat);

        $this->sql =
            "SELECT " . $table . " (urun_ad, fiyat) VALUES ('" . $urun_ad . "','" . $fiyat . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }



}

?>
