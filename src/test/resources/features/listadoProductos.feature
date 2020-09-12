@ListadoProductos
Feature: Listado de Productos

  @High
  Scenario Outline: Ir a la segunda pagina del listado e ingresar al detalle del ultimo producto
    Given Un cliente ingresa a la aplicacion de MercadoLibre
    When Ingresa en el campo de busqueda la palabra "<producto>" y la selecciona del combo
    And Se dirige a la "2" pagina del listado
    Then Se visualizan 50 productos que contienen la palabra "<producto>"

    Examples: 
      | producto  		 |
      | Teclado Genius |
