'use strict'
app
        .factory('UsuarioService2222', function ($http, config) {
            var url = "https://nihongakko.com/webservice/rest/server.php?wstoken=2366ace0549fd307e165484b450cb320&wsfunction=core_course_get_courses&moodlewsrestformat=json";
            return {
                listar: function () {
                    return $http.get(url);
                },
                listarUsuarios: function () {
                    return $http.get("https://nihongakko.com/webservice/rest/server.php?wstoken=2366ace0549fd307e165484b450cb320&wsfunction=core_user_get_users&moodlewsrestformat=json&criteria[0][key]=email&criteria[0][value]=%%");
                },
                listarCategoriasFilter: function (id) {
                    return $http.get("https://nihongakko.com/webservice/rest/server.php?wstoken=2366ace0549fd307e165484b450cb320&wsfunction=core_course_get_categories&moodlewsrestformat=json&criteria[0][key]=parent&criteria[0][value]=" + id);
                },
                listarPorId: function (id) {
                    return $http.get(url + "/" + id);
                },
                generarReporte: function (ci, nom, ape, usuLogin) {
                    return $http.get(url + "/generarReporte/" + ci + "/" + nom + "/" + ape + "/" + usuLogin);
                },
                crear: function (data) {
                    return $http.post("https://nihongakko.com/webservice/rest/server.php?wstoken=2366ace0549fd307e165484b450cb320&moodlewsrestformat=json&wsfunction=core_user_create_users&" + data);
                },
                actualizar: function (data) {
                    return $http.get("https://nihongakko.com/webservice/rest/server.php?wstoken=2366ace0549fd307e165484b450cb320&wsfunction=core_user_update_users&moodlewsrestformat=json&" + data);
                },
                actualizarNada: function (data) {
                    return $http.put(url, data);
                },
                eliminar: function (id) {
                    return $http.delete(url + "/" + id);
                },
                fetch: function (limit, offset) {
                    return $http.get(url + "/fetch/" + limit + "/" + offset);
                },
                fetchFiltro: function (limit, offset, nom, ape, ci) {
                    return $http.get(url + "/fetchFiltro/" + limit + "/" + offset + "/" + nom + "/" + ape + "/" + ci);
                },
                count: function () {
                    return $http.get(url + "/count/");
                },
                countFiltro: function (nom, ape, ci) {
                    return $http.get(url + "/countFiltro/" + nom + "/" + ape + "/" + ci);
                }
            };
        });