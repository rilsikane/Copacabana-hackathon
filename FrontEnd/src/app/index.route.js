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
        controller: 'MainController'
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
      .state('app.reservation',{
        url: '/reservation',
        views:{
          'app' :{
             templateUrl: 'app/reservation/reservation.html',
          }
        }
      })
      .state('app.payment',{
        url: '/payment',
        views:{
          'app' :{
             templateUrl: 'app/payment/payment.html',
          }
        }
      })
      



    $urlRouterProvider.otherwise('/app/home');
  }

})();
