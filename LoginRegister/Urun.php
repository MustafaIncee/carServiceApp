<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "arabaservisi";

// Veritabanına bağlanma
$conn = new mysqli($servername, $username, $password, $dbname);

// Bağlantıyı kontrol etme
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Verileri seçme
$sql = "SELECT * FROM urunler";
$result = $conn->query($sql);

// Verileri JSON formatında döndürme
$data = array();
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
}
echo json_encode($data);

// Bağlantıyı kapatma
$conn->close();
?>