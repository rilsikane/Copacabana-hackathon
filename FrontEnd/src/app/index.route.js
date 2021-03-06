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
        url: '/reservation/',
        params: { 
          // here we define default value for foo
          // we also set squash to false, to force injecting
          // even the default value into url
          room: {

          },
          hiddenParam: 'YES',
        },
        views:{
          'app' :{
             templateUrl: 'app/reservation/reservation.html',
             controller:'ReservationController'
          }
        }
      })
      .state('app.payment',{
        url: '/payment',
        views:{
          'app' :{
             templateUrl: 'app/payment/payment.html',
             controller: 'PaymentController'
          }
        }
      })
      .state('app.report',{
        url: '/report',
        views:{
          'app' :{
             templateUrl: 'app/report/report.html',
             controller: 'ReportController'
          }
        }
      })
      
      



    $urlRouterProvider.otherwise('/app/home');
  }

})();
