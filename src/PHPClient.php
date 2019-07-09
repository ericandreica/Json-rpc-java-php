<?php
class PHPClient {
    public function urlCall($urlServ, $body) {
        $options = array (
            CURLOPT_URL => $urlServ,
            CURLOPT_HEADER => false,
            CURLOPT_POST => true,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_POSTFIELDS => $body
        );
        try {
            $conn = curl_init ();
            curl_setopt_array ( $conn, $options );
            $response = curl_exec ( $conn );
            curl_close ( $conn );
            return $response;
        } catch (Exception $e) {
            return '{"error":"'.strval($e).'"}';
        }
    }
    function __construct($urlServ)
    {
        echo "Client JSON-RPC PHP: " . $urlServ . "\n";
        $var = 1;
        while (true) {
            $this->menu();
            echo "Option: ";
            $op = stream_get_line(STDIN, 1024, PHP_EOL);
            $str = strval($op);
            switch ($str) {
                case "0":
                    echo "Exit...";
                    $var = 0;
                    break;
                case "1":
                    echo "List is: ".$this->urlCall($urlServ, '{"method":"listAll","params":{}}');
                    break;
                case "2":
                    echo "File name: ";
                    $name = stream_get_line(STDIN, 1024, PHP_EOL);
                    echo $this->urlCall($urlServ, '{"method":"getByName","params":{"nume":'.$name.'}}');
                    break;
                case "3":
                    echo "Content: ";
                    $content = stream_get_line(STDIN, 1024, PHP_EOL);
                    echo $this->urlCall($urlServ, '{"method":"getByContentString","params":{"content":'.$content.'}}');
                    break;
                case "4":
                    echo "Content: ";
                    $bytes = stream_get_line(STDIN, 1024, PHP_EOL);
                    echo $this->urlCall($urlServ, '{"method":"getByContentBytes","params":{"content":"'.$bytes.'"}}');
                    break;
                case "5":
                    echo "Hash: ";
                    $hash = stream_get_line(STDIN, 1024, PHP_EOL);
                    echo $this->urlCall($urlServ, '{"method":"getByHash","params":{"myhash":'.$hash.'}}');
                    break;
                default:
                    echo "Bad input\n";
            }
            echo "\n";
            if($var == '0')
                break;
        }
    }
    function menu(){
        echo "1:List\n";
        echo "2:Get by name\n";
        echo "3:Search by content as string\n";
        echo "4:Search by content as bytes\n";
        echo "5:Search by hash\n";
        echo "0:Exit\n";
    }

}
if (isset($_GET['urlServ']))
    new PHPClient($_GET['urlServ']);
else
    new PHPClient("http://localhost:8080/JavaServer");
?>
