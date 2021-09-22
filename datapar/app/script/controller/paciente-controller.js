'use strict'
app
        .controller('pacienteCtrl', ['$state', '$scope', 'config', '$cookies', 'PacienteService', 'UsuarioService', function ($state, $scope, config, $cookies, PacienteService, UsuarioService) {
                $scope.formData = {};
                $scope.people = [];
                $scope.personaAgregar = {};
                $scope.personaUsuario = {};
                $scope.personaModificar = {};

                var cabecera = {headers: {
                        'Authorization': $cookies.getObject("access"),
//                            'Accept': 'application/json;odata=verbose',
//                            "X-Testing": "testing"
                    }
                };

                $scope.salirData = function () {
                    $cookies.putObject("access", null);
                    $cookies.putObject("idusuario", null);
                    location.href = "../#";
                };

                $scope.listar = function () {
                    PacienteService.listar(cabecera).success(function (data) {
                        if (data.length > 0) {
                            $scope.people = data;
                        }
                    }).error(function (e) {
                        swal("Mensaje del Sistema!", "No se pudo establecer conexión con el Servidor", "error");
                    });
                }

                $scope.usuario = function (personas) {
                    $("#visibleMod").val(personas.visible + "");
                    $('#modalUsuario').modal('show');
                    $scope.personaUsuario.usuario = "";
                    $scope.personaUsuario.password = "";
                    $scope.personaUsuario.idpaciente = personas.id;
                    UsuarioService.listarPorIdPaciente(personas.id, cabecera).success(function (data) {
                        $("#visibleMod").val(personas.visible + "");
                        $('#modalUsuario').modal('show');
                        $scope.personaUsuario.usuario = data.usuario;
                        $scope.personaUsuario.password = data.password;
                        $scope.personaUsuario.idpaciente = personas.id;
                        $scope.personaUsuario.id = data.id;
                    }).error(function (e) {
//                        swal("Mensaje del Sistema!", "No se pudo establecer conexión con el Servidor", "error");
                    });
                };
                $scope.registrarUsuario = function () {
                    if ($scope.personaUsuario.usuario === "" || $scope.personaUsuario.usuario === undefined || $scope.personaUsuario.usuario === null ||
                            $scope.personaUsuario.password === "" || $scope.personaUsuario.password === undefined || $scope.personaUsuario.password === null) {
                        swal("Mensaje del Sistema!", "Los campos no debe quedar vacío", "error");
                    } else {
                        UsuarioService.crear($scope.personaUsuario, cabecera).success(function (data) {
                            if (data.usuario !== undefined || data.usuario !== "") {
                                swal("Mensaje del Sistema!", "Datos Agregados exitosamente", "success");
                                $('#modalUsuario').modal('hide');
                                $scope.listar();
                            } else {
                                swal("Mensaje del Sistema!", "Datos no Agregados, " + data.message, "error");
                            }
                        }).error(function (e) {
                            swal("Mensaje del Sistema!", "No se pudo establecer conexión con el Servidor", "error");
                        });
                    }

                };

                $scope.modificar = function (personas) {
                    $("#visibleMod").val(personas.visible + "");
                    $('#modalModificar').modal('show');
                    $scope.personaModificar.id = personas.id;
                    $scope.personaModificar.nombre = personas.nombre;
                    $scope.personaModificar.cedula = personas.cedula;
                    $scope.personaModificar.telefono = personas.telefono;
                    $scope.personaModificar.direccion = personas.direccion;
                    $scope.personaModificar.email = personas.email;
                    $("#sexoModificar").val(personas.sexo)
                    $scope.personaModificar.sexo = $("#sexoModificar").val();
                    $scope.personaModificar.edad = personas.edad;
                };

                if ($cookies.getObject("access") === null || $cookies.getObject("access") === undefined) {
                    $scope.salirData();
                } else {
                    $scope.listar();
                }

                $scope.registrar = function () {
                    if ($scope.personaAgregar.nombre === "" || $scope.personaAgregar.nombre === undefined || $scope.personaAgregar.nombre === null ||
                            $scope.personaAgregar.cedula === "" || $scope.personaAgregar.cedula === undefined || $scope.personaAgregar.cedula === null) {
                        swal("Mensaje del Sistema!", "Los campos Nombre, y Cédula no debe quedar vacío", "error");
                    } else {
                        $scope.personaAgregar.sexo = $("#sexo").val();
                        console.log("PERSONA ", $scope.personaAgregar);
                        PacienteService.crear($scope.personaAgregar, cabecera).success(function (data) {
                            if (data.nombre !== undefined || data.nombre !== "") {
                                swal("Mensaje del Sistema!", "Datos Agregados exitosamente", "success");
                                $('#modalAgregar').modal('hide');
                                $scope.listar();
                            } else {
                                swal("Mensaje del Sistema!", "Datos no Agregados, " + data.message, "error");
                            }
                        }).error(function (e) {
                            swal("Mensaje del Sistema!", "No se pudo establecer conexión con el Servidor", "error");
                        });
                    }
                };
                $scope.eliminar = function (persona) {
                    swal({
                        title: "Mensaje del Sistema",
                        text: "¿ Seguro que deseas eliminar " + persona.nombre + " ?",
                        type: "warning",
                        showCancelButton: true,
                        cancelButtonText: "Cancelar",
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "Confirmar",
                        closeOnConfirm: false},
                            function () {
                                PacienteService.eliminar(persona.id, cabecera).success(function (data) {
                                    if (data.cedula !== undefined || data.cedula !== "") {
                                        swal('Eliminado!',
                                                'Haz eliminado exitosamente.',
                                                'success'
                                                );
                                        $scope.listar();
                                    } else {
                                        swal("Mensaje del Sistema!", "No se pudo eliminar, verifique las dependencias", "error");
                                    }
                                }).error(function (e) {
                                    swal("Mensaje del Sistema!", "No se pudo eliminar, verifique las dependencias", "error");
                                });
                            });
                };
                $scope.atras = function () {
                    location.reload();
                }
                $scope.actualizar = function () {
                    if ($scope.personaModificar.nombre === "" || $scope.personaModificar.nombre === undefined || $scope.personaModificar.nombre === null ||
                            $scope.personaModificar.cedula === "" || $scope.personaModificar.cedula === undefined || $scope.personaModificar.cedula === null) {
                        swal("Mensaje del Sistema!", "Los campos Nombre, y Cédula no debe quedar vacío", "error");
                    } else {
                        $scope.personaModificar.sexo = $("#sexoModificar").val();
                        console.log("PERSONA ", $scope.personaModificar);
                        PacienteService.actualizar($scope.personaModificar, cabecera).success(function (data) {
                            if (data.nombre !== undefined || data.nombre !== "") {
                                swal("Mensaje del Sistema!", "Datos Actualizados exitosamente", "success");
                                $('#modalModificar').modal('hide');
                                $scope.listar();
                            } else {
                                swal("Mensaje del Sistema!", "Datos no Agregados, " + data.message, "error");
                            }
                        }).error(function (e) {
                            swal("Mensaje del Sistema!", "No se pudo establecer conexión con el Servidor", "error");
                        });
                    }
                };
            }]);
        