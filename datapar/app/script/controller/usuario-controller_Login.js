'use strict'
appLogin
        .controller('loginCtrl', ['$state', '$scope', 'config', 'UsuarioService', '$cookies', function ($state, $scope, config, UsuarioService, $cookies) {
                $scope.formData = {};

                $("#btnResetFaliido").css("display", "none");//hide();
                $("#usuario").focus();

                $scope.ingresar = function () {
                    if ($scope.formData.usuario === undefined || $scope.formData.usuario === "") {
                        $("#mensajeError").html('<div class="alert alert-danger">El campo Usuario no debe quedar vacío</div>');
                        $("#usuario").focus();
                    } else if ($scope.formData.password === undefined || $scope.formData.password === "") {
                        $("#mensajeError").html('<div class="alert alert-danger">El campo Clave no debe quedar vacío</div>');
                        $("#pass").focus();
                    } else {
                        UsuarioService.login($scope.formData).success(function (data) {
                            if (data.usuario === null || data.usuario === "null") {
                                swal("Mensaje del Sistema!", "Usuario o Contraseña inválida", "error");
                            } else {
                                $cookies.putObject("access", data.password);
                                $cookies.putObject("idusuario", data.id);
                                location.href = "menu.html";
                            }
                        });
                    }
                };
                $("#usuario").keyup(function () {
                    if ($scope.formData.usuario !== "") {
                        $("#mensajeError").html("");
                    }
//                    console.log("OU -> "+());
                });
                $("#pass").keyup(function () {
                    if ($scope.formData.password !== "") {
                        $("#mensajeError").html("");
                    }
//                    console.log("BYE -> "+($scope.formData.password));
                });
            }])
        .directive('myEnter', ['$cookies', 'UsuarioService', function ($cookies, UsuarioService) {
                return function (scope, element, attrs) {
                    element.bind("keydown keypress", function (event) {
                        if (event.which === 13) {
                            scope.$apply(function () {
                                scope.$eval(attrs.myEnter);
                                scope.ingresar = function () {
                                    if (scope.formData.usuario === undefined || scope.formData.usuario === "") {
                                        $("#mensajeError").html('<div class="alert alert-danger">El campo Usuario no debe quedar vacío</div>');
                                        $("#usuario").focus();
                                    } else if (scope.formData.password === undefined || scope.formData.password === "") {
                                        $("#mensajeError").html('<div class="alert alert-danger">El campo Clave no debe quedar vacío</div>');
                                        $("#pass").focus();
                                    } else {

                                        UsuarioService.login(scope.formData).success(function (data) {
                                            if (data.usuario === null || data.usuario === "null") {
                                                swal("Mensaje del Sistema!", "Usuario o Contraseña inválida", "error");
                                            } else {
                                                $cookies.putObject("access", data.password);
                                                $cookies.putObject("idusuario", data.id);
                                                location.href = "menu.html";
                                            }
                                        });
                                    }
                                };
                                scope.ingresar();
                            });
                            event.preventDefault();
                        }
                    });
                };
            }])
        .directive('myEnterRegister', ['$cookies', 'UsuarioService', function ($cookies, UsuarioService) {
                return function (scope, element, attrs) {
                    element.bind("keydown keypress", function (event) {
                        if (event.which === 13) {
                            scope.$apply(function () {
                                scope.$eval(attrs.myEnter);
                                scope.crearCuenta = function () {
                                    var valor = true;
                                    if ($("#nombrePersona").val() === "") {
                                        $("#nombrePersona").css('border', "red solid 1px");
                                        $("#nomPer").html("Nombre es un campo requerido");
                                        valor = false;
                                    } else {
                                        $("#nombrePersona").css('border', "#CCC solid 1px");
                                        $("#nomPer").html("");
                                    }

                                    if ($("#apellidoPersona").val() === "") {
                                        $("#apellidoPersona").css('border', "red solid 1px");
                                        $("#apePer").html("Apellido es un campo requerido");
                                        valor = false;
                                    } else {
                                        $("#apellidoPersona").css('border', "#CCC solid 1px");
                                        $("#apePer").html("");
                                    }

                                    if ($("#emailPersona").val() === "") {
                                        $("#emailPersona").css('border', "red solid 1px");
                                        $("#emailPer").html("Email es un campo requerido");
                                        valor = false;
                                    } else {
                                        $("#emailPersona").css('border', "#CCC solid 1px");
                                        $("#emailPer").html("");
                                    }

                                    if ($("#usuario").val() === "") {
                                        $("#usuario").css('border', "red solid 1px");
                                        $("#usuUsu").html("Email es un campo requerido");
                                        valor = false;
                                    } else {
                                        $("#usuario").css('border', "#CCC solid 1px");
                                        $("#usuUsu").html("");
                                    }

                                    if ($("#password").val() === "") {
                                        $("#password").css('border', "red solid 1px");
                                        $("#claUsu").html("Contraseña es un campo requerido");
                                        valor = false;
                                    } else {
                                        $("#password").css('border', "#CCC solid 1px");
                                        $("#claUsu").html("");
                                    }

                                    if ($("#passwordConfirm").val() === "") {
                                        $("#passwordConfirm").css('border', "red solid 1px");
                                        $("#claUsuConf").html("Contraseña de confirmación es un campo requerido");
                                        valor = false;
                                    } else {
                                        $("#passwordConfirm").css('border', "#CCC solid 1px");
                                        $("#claUsuConf").html("");
                                    }

                                    var email = $("#emailPersona").val();
                                    if (!email.includes("@")) {
                                        $("#emailPersona").css('border', "red solid 1px");
                                        $("#emailPer").html("Debe contener @");
                                        valor = false;
                                    } else {
                                        $("#emailPersona").css('border', "#CCC solid 1px");
                                        $("#emailPer").html("");
                                    }

                                    if (!valor) {
                                        swal("Mensaje del Sistema!", "Complete los campos requeridos", "error");
                                    } else {
                                        if (scope.usuario.password !== scope.usuario.passwordConfirm) {
//                if ($("#password").val() !== $("#passwordConfirm").val()) {
                                            swal("Mensaje del Sistema!", "Los campos Contraseña y confirmación no coinciden", "error");
                                            $("#password").val("");
                                            $("#passwordConfirm").val("");
                                            $("#password").focus();
                                            valor = false;
                                        } else {
                                            UsuarioService.verificarUsuarioExistencia(scope.usuario.usuario).success(function (data) {
                                                if (data === false) {
                                                    PersonaService.crear(scope.cuenta).success(function (data) {
                                                        if (data === 0) {
                                                            swal("Mensaje del Sistema!", "No se pudo crear persona! El email ingresado ya está siendo utilizado por otro usuario.", "error");
                                                        } else {
                                                            scope.crearUsuarioCuenta(data);
                                                        }
                                                    }).error(function (e) {
                                                        swal("Mensaje del Sistema!", "No se pudo establecer conexión con el Servidor", "error");
                                                    });
                                                } else {
                                                    swal("Mensaje del Sistema!", "El usuario ingresado ya existe!", "error");
                                                }
                                            }).error(function (e) {
                                                swal("Mensaje del Sistema!", "No se pudo establecer conexión con el Servidor", "error");
                                            });
                                        }
                                    }

                                };
                                scope.crearCuenta();
                            });
                            event.preventDefault();
                        }
                    });
                };
            }]);