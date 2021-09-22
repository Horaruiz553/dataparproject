var app = angular.module('dataparAdminApp', ['ui.router', 'ngCookies', 'ngAnimate', 'ngSanitize', 'ui.bootstrap', 'AxelSoft', 'ng-ip-address', 'zingchart-angularjs']);

app.constant('config', {
    'backend': 'http://localhost:8080/datapar'
});

app.config(function ($stateProvider, $httpProvider) {
    $stateProvider.state('menu-user', {
        url: 'menu',
        templateUrl: 'partial/datapar/home.html',
    });
    $stateProvider.state('pacientes', {
        url: 'pacientes',
        templateUrl: 'partial/datapar/pacientes/index.html',
        controller: 'pacienteCtrl'
    });
    $stateProvider.state('medicos', {
        url: 'medicos',
        templateUrl: 'partial/datapar/medicos/index.html',
        controller: 'medicoCtrl'
    });
    $stateProvider.state('usuarios', {
        url: 'usuarios',
        templateUrl: 'partial/datapar/usuarios/index.html',
        controller: 'usuarioSistemaCtrl'
    });
    $stateProvider.state('registros', {
        url: 'registros',
        templateUrl: 'partial/datapar/registros/index.html',
        controller: 'registroCtrl'
    });

})
        .run(function ($state) {
            $state.go('menu-user');
        });