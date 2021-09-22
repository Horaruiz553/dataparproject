'use strict'
appLogin
        .factory('UsuarioService', function ($http, config) {
            var url = config.backend + "/usuario";
            return {
                listar: function () {
                    return $http.get(url);
                },
                listarPorId: function (id) {
                    return $http.get(url + "/" + id);
                },
                login: function (data) {
                    return $http.post(url + "/user", data);
                },
                crear: function (data) {
                    return $http.post(url, data);
                },
                actualizar: function (data) {
                    return $http.put(url, data);
                },
                eliminar: function (id) {
                    return $http.delete(url + "/" + id);
                }
            };
        });