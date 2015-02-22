var app = angular.module('localmarketApp', ['ngRoute'])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/advertise', {
        templateUrl: 'views/advertise.html',
        controller: 'AdvertiseCtrl',
        controllerAs: 'referCtrl'
      })
      .when('/search', {
        templateUrl: 'views/search.html',
        controller: 'SearchCtrl',
        controllerAs: 'searchCtrl'
      })
      .when('/list', {
        templateUrl: 'views/list.html',
        controller: 'ListCtrl',
        controllerAs: 'listCtrl'
      })
       .when('/thanks', {
        templateUrl: 'views/thanks.html'
      })
       .when('/interest', {
        templateUrl: 'views/interest.html',
        controller: 'InterestCtrl',
        controllerAs: 'interestCtrl'
      })
      .when('/single', {
        templateUrl: 'views/single.html',
        controller: 'SingleCtrl',
        controllerAs: 'singleCtrl'
      })
       .when('/default', {
        templateUrl: 'views/default.html',
        controller: 'DefaultCtrl',
        controllerAs: 'defaultCtrl'
      })
      .otherwise({
        redirectTo: '/default'
      });
  });

//use only for local testing
// app.constant('host', 'http://localhost:8080/lm');
app.constant('host', 'http://54.149.156.200:8080);
