'use strict'
app
        .factory('PacienteService', function ($http, config) {
            var url = config.backend + "/paciente/";
            return {
                listar: function (headerOptions) {
                    return $http.get(url, headerOptions);
                },
                listarPorId: function (id) {
                    return $http.get(url + "/" + id);
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