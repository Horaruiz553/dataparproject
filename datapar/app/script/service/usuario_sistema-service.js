'use strict'
app
        .factory('UsuarioService', function ($http, config) {
            var url = config.backend + "/usuario/";
            return {
                login: function (data) {
                    return $http.post(url + "user", data);
                },
                listarPorId: function (id, headerOptions) {
                    return $http.get(url + id, headerOptions);
                },
                listarPorIdPaciente: function (id, headerOptions) {
                    return $http.get(url + "paciente/" + id, headerOptions);
                },
                listarPorIdMedico: function (id, headerOptions) {
                    return $http.get(url + "medico/" + id, headerOptions);
                },
                crear: function (data, headerOptions) {
                    return $http.post(url, data, headerOptions);
                },
                actualizar: function (data, headerOptions) {
                    return $http.post(url, data, headerOptions);
                },
                eliminar: function (id, headerOptions) {
                    return $http.delete(url + id, headerOptions);
                }
            };
        });