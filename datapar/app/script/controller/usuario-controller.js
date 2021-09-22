'use strict'
app
        .controller('menuCtrl', ['$state', '$scope', 'config', '$cookies', function ($state, $scope, config, $cookies) {
                $scope.formData = {};

                $scope.salirData = function () {
                    $cookies.putObject("access", null);
                    $cookies.putObject("idusuario", null);
                    location.href = "../#";
                };
                
                $scope.reload = function () {
                    location.reload();
                };
                
                $scope.addClase = function (val) {
                    var menues = ['med', 'pac', 'reg'];
                    for (var i = 0; i < menues.length; i++) {
                        if (val.toLowerCase() === menues[i].toLowerCase()) {
                            $("#" + val).addClass("active");
                        } else {
                            $("#" + menues[i]).removeClass("active");
                        }
                    }
                };

                if ($cookies.getObject("access") === null || $cookies.getObject("access") === undefined) {
                    $scope.salirData();
                }
            }]);
        