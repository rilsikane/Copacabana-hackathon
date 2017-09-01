(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .controller('MainController', MainController)
    .controller('ModalInstanceCtrl', ModalInstanceCtrl);

  /** @ngInject */
  function MainController($uibModal,$scope) {

    $scope.login = function(){
         var modalInstance = $uibModal.open({
          animation: true,
          templateUrl: 'myModalContent.html',
          controller: 'ModalInstanceCtrl',
          size: 'lg'
      });

      
    }
    
  }
   function ModalInstanceCtrl(){
    }
})();
