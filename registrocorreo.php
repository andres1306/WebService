<?php 
if (isset($_REQUEST['user']) && isset($_REQUEST['clave']) && isset($_REQUEST['nombre']) && isset($_REQUEST['correo']))
{
/*$usr=$_POST['usr'];
$clave=$_POST['clave'];
$nombre=$_POST['nombre'];
$correo=$_POST['correo'];*/
$usr = $_REQUEST['usr'];
$clave = $_REQUEST['clave'];
$nombre = $_REQUEST['nombre'];
$correo = $_REQUEST['correo'];
$cnx =  mysqli_connect("localhost","root","","empresa6am") or die("Ha sucedido un error inexperado en la conexion de la base de datos");
$result = mysqli_query($cnx,"select correo from usuarios where correo = '$correo'");
if (mysqli_num_rows($result)==0)
{
	mysqli_query($cnx,"INSERT INTO usuarios (usr,nombre,correo,clave) VALUES ('$usr','$nombre','$correo','$clave')");	
}
else							
{
	echo "Correo ya existe....";
}
mysqli_close($cnx);
}
else
{
	echo "Debe especificar usr, clave, nombre y correo, respectivamente";
}
?>