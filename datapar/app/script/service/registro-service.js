'use strict'
app
        .factory('RegistroService', function ($http, config) {
            var url = config.backend + "/registro/";
            return {
                listar: function (headerOptions) {
                    return $http.get(url, headerOptions);
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