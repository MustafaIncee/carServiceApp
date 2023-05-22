<?php
class DataBaseConfig
{
    public $servername;
    public $username;
    public $password;
    public $databasename;
    public $conn;
    public function __construct()
    {
        $this->servername = 'localhost';
        $this->username = 'root';
        $this->password = '';
        $this->databasename = 'arabaservisi';
        $this->conn = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        if(!$this->conn)
            die("Connection failed: " . mysqli_connect_errno());

    }
}
?>