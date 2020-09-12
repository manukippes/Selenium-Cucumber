@DetalleProducto
Feature: Detalle de Productos
  
@High 
Scenario: Ingresar al detalle de una TV y verificar su precio
 Given Un cliente ingresa a la aplicacion de MercadoLibre
 When en la categoria tecnologia selecciona TVS
 And ingresar al detalle de la tv "Smart TV Philips 5000 Series 43PFG5813/77 LED Full HD 43\""
 Then Visualiza estos valores
   	| precio | estado | resolucion |
    | 35.714 | Nuevo | Su resolución es Full HD. |
