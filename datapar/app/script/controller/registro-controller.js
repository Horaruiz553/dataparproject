'use strict'
app
        .controller('registroCtrl', ['$state', '$scope', 'config', '$cookies', 'RegistroService', 'UsuarioService', 'PacienteService', 'MedicoService', function ($state, $scope, config, $cookies, RegistroService, UsuarioService, PacienteService, MedicoService) {
                $scope.formData = {};
                $scope.people = [];
                $scope.personaAgregar = {};
                $scope.personaUsuario = {};
                $scope.personaModificar = {};
                const mapeo = new Map();
                const mapeoNombre = new Map();


                var cabecera = {headers: {
                        'Authorization': $cookies.getObject("access"),
//                            'Accept': 'application/json;odata=verbose',
//                            "X-Testing": "testing"
                    }
                };
                
                $scope.listar = function () {
                    RegistroService.listar(cabecera).success(function (data) {
                        if (data.length > 0) {
                            $scope.people = data;
                        }
                    }).error(function (e) {
                        swal("Mensaje del Sistema!", "No se pudo establecer conexión con el Servidor", "error");
                    });
                }

                $scope.salirData = function () {
                    $cookies.putObject("access", null);
                    $cookies.putObject("idusuario", null);
                    location.href = "../#";
                };

                PacienteService.listar(cabecera).success(function (data) {
                    if (data.length > 0) {
                        var detalle = "";
                        for (var i = 0, max = data.length; i < max; i++) {
                            mapeo.set(data[i].id, data[i].cedula);
                            mapeoNombre.set(data[i].id, data[i].nombre);
                            detalle = '<option value="' + data[i].id + '">' + data[i].nombre + '</option>';
                        }
                        $("#pacienteCombo").html(detalle);
                        $("#pacienteComboActualizar").html(detalle);
                    }
                }).error(function (e) {
                    swal("Mensaje del Sistema!", "No se pudo establecer conexión con el Servidor", "error");
                });

                MedicoService.listar(cabecera).success(function (data) {
                    if (data.length > 0) {
                        var detalle = "";
                        for (var i = 0, max = data.length; i < max; i++) {
                            detalle = '<option value="' + data[i].id + '">' + data[i].nombre + '</option>';
                        }
                        $("#medicoCombo").html(detalle);
                        $("#medicoComboActualizar").html(detalle);
                    }
                }).error(function (e) {
                    swal("Mensaje del Sistema!", "No se pudo establecer conexión con el Servidor", "error");
                });


                $scope.modificar = function (personas) {
                    $("#visibleMod").val(personas.visible + "");
                    $('#modalModificar').modal('show');
                    $("#pacienteComboActualizar").val(personas.idpaciente);
                    $scope.personaModificar.idpaciente = personas.idpaciente;
                    $("#medicoComboActualizar").val(personas.idmedico);
                    $scope.personaModificar.idmedico = personas.idmedico;
                    $scope.personaModificar.cedula = personas.cedula;
                    $scope.personaModificar.nombre = personas.telefono;
                    $scope.personaModificar.consulta = personas.consulta;
                    $scope.personaModificar.examen = personas.examen;
                    $scope.personaModificar.id = personas.id;
                };

                if ($cookies.getObject("access") === null || $cookies.getObject("access") === undefined) {
                    $scope.salirData();
                } else {
                    $scope.listar();
                }

                $scope.registrar = function () {
                    if ($scope.personaAgregar.examen === "" || $scope.personaAgregar.examen === undefined || $scope.personaAgregar.examen === null ||
                            $scope.personaAgregar.consulta === "" || $scope.personaAgregar.consulta === undefined || $scope.personaAgregar.consulta === null) {
                        swal("Mensaje del Sistema!", "Los campos no debe quedar vacío", "error");
                    } else {
                        $scope.personaAgregar.idpaciente = $("#pacienteCombo").val()
                        $scope.personaAgregar.idmedico = $("#medicoCombo").val()
                        $scope.personaAgregar.cedula = mapeo.get(parseInt($("#pacienteCombo").val()))
                        $scope.personaAgregar.nombre = mapeoNombre.get(parseInt($("#pacienteCombo").val()))
                        RegistroService.crear($scope.personaAgregar, cabecera).success(function (data) {
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
                                RegistroService.eliminar(persona.id, cabecera).success(function (data) {
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
                                    swal("Mensaje del Sistema!", "No se pudo establecer conexión con el Servidor", "error");
                                });
                            });
                };
                $scope.atras = function () {
                    location.reload();
                }
                $scope.actualizar = function () {
                    if ($scope.personaModificar.examen === "" || $scope.personaModificar.examen === undefined || $scope.personaModificar.examen === null ||
                            $scope.personaModificar.consulta === "" || $scope.personaModificar.consulta === undefined || $scope.personaModificar.consulta === null) {
                        swal("Mensaje del Sistema!", "Los campos no debe quedar vacío", "error");
                    }  else {
                        $scope.personaModificar.idpaciente = $("#pacienteCombo").val()
                        $scope.personaModificar.idmedico = $("#medicoCombo").val()
                        $scope.personaModificar.cedula = mapeo.get(parseInt($("#pacienteCombo").val()))
                        $scope.personaModificar.nombre = mapeoNombre.get(parseInt($("#pacienteCombo").val()))
                        RegistroService.actualizar($scope.personaModificar, cabecera).success(function (data) {
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
        