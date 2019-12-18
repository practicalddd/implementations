/**
 * @license
 * Copyright (c) 2014, 2019, Oracle and/or its affiliates.
 * The Universal Permissive License (UPL), Version 1.0
 * @ignore
 */
'use strict';

/**
 * Example of Require.js boostrap javascript
 */

 // The UserAgent is used to detect IE11. Only IE11 requires ES5.
(function () {
  
  function _ojIsIE11() {
    var nAgt = navigator.userAgent;
    return nAgt.indexOf('MSIE') !== -1 || !!nAgt.match(/Trident.*rv:11./);
  };
  var _ojNeedsES5 = _ojIsIE11();

  requirejs.config(
    {
      baseUrl: 'js',

      // Path mappings for the logical module names
      paths:
      // injector:mainReleasePaths
      {
        'knockout': 'libs/knockout/knockout-3.5.0.debug',
        'jquery': 'libs/jquery/jquery-3.4.1',
        'jqueryui-amd': 'libs/jquery/jqueryui-amd-1.12.1',
        'hammerjs': 'libs/hammer/hammer-2.0.8',
        'ojdnd': 'libs/dnd-polyfill/dnd-polyfill-1.0.1',
        'ojs': 'libs/oj/v8.0.0/debug' + (_ojNeedsES5 ? '_es5' : ''),
        'ojL10n': 'libs/oj/v8.0.0/ojL10n',
        'ojtranslations': 'libs/oj/v8.0.0/resources',
        'text': 'libs/require/text',
        'signals': 'libs/js-signals/signals',
        'customElements': 'libs/webcomponents/custom-elements.min',
        'proj4': 'libs/proj4js/dist/proj4-src',
        'css': 'libs/require-css/css',
        'touchr': 'libs/touchr/touchr',
        'corejs' : 'libs/corejs/shim',
        'regenerator-runtime' : 'libs/regenerator-runtime/runtime'
      }
      // endinjector
    }
  );
}());

/**
 * A top-level require call executed by the Application.
 * Although 'knockout' would be loaded in any case (it is specified as a  dependency
 * by some modules), we are listing it explicitly to get the reference to the 'ko'
 * object in the callback
 */
require(['ojs/ojbootstrap', 'knockout', 'appController', 'ojs/ojlogger', 'ojs/ojrouter', 'ojs/ojknockout',
  'ojs/ojmodule', 'ojs/ojnavigationlist', 'ojs/ojbutton', 'ojs/ojtoolbar'],
  function (Bootstrap, ko, app, Logger, Router) { // this callback gets executed when all required modules are loaded
    Bootstrap.whenDocumentReady().then(
      function() {

        function init() {
            Router.sync().then(
              function () {
                app.loadModule();
                // Bind your ViewModel for the content of the whole page body.
                ko.applyBindings(app, document.getElementById('globalBody'));
              },
              function (error) {
                Logger.error('Error in root start: ' + error.message);
              }
            );
          }

          // If running in a hybrid (e.g. Cordova) environment, we need to wait for the deviceready
          // event before executing any code that might interact with Cordova APIs or plugins.
          if (document.body.classList.contains('oj-hybrid')) {
            document.addEventListener("deviceready", init);
          } else {
            init();
          }
        });
  }
);
