<?php 
if (isset($_REQUEST['correo']) && isset($_REQUEST['clave']) && isset($_REQUEST['nombre']) && isset($_REQUEST['correo']))
{
	$usr=$_REQUEST['usr'];
	$clave=$_REQUEST['clave'];
	$nombre=$_REQUEST['nombre'];
	$correo=$_REQUEST['correo'];
	$cnx =  mysqli_connect("localhost","root","","empresa6am") or die("Ha sucedido un error inexperado en la conexion de la base de datos");
	$result = mysqli_query($cnx,"select usr from usuarios where usr = '$usr'");
	if (mysqli_num_rows($result)>0)
	{
		mysqli_query($cnx,"UPDATE usuarios SET nombre='$nombre',correo='$correo',clave='$clave' WHERE usr = '$usr'");	
	}
	else
	{
		echo "Usuario No existe....";
	}
	mysqli_close($cnx);
}
else
{
	echo "Debe especificar usr, clave, nombre y correo, respectivamente";
}
?>
