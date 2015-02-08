angular.module('localmarketApp', ['ngRoute'])
  .config(function ($routeProvider) {

    $routeProvider
      .when('/refer', {
        templateUrl: 'views/refer.html',
        controller: 'ReferCtrl',
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
      .otherwise({
        redirectTo: '/interest'
      });
  });
