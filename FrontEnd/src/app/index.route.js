(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('app',{
        url: '/app',
        abstract:true,
        templateUrl: 'app/main/main.html',
        controller: 'MainController',
        controllerAs: 'main'
      })
      .state('app.home',{
        url: '/home',
        views:{
          'app' :{
             templateUrl: 'app/home/home.html',
          }
        }
      })
      .state('app.search',{
        url: '/search',
        views:{
          'app' :{
             templateUrl: 'app/search/search.html',
             controller: 'SearchController'

          }
        }
      })
      



    $urlRouterProvider.otherwise('/app/home');
  }

})();
