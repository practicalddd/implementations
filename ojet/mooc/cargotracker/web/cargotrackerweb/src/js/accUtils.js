/**
 * @license
 * Copyright (c) 2014, 2019, Oracle and/or its affiliates.
 * The Universal Permissive License (UPL), Version 1.0
 * @ignore
 */
/**
 * @license
 * Copyright (c) 2014, 2018, Oracle and/or its affiliates.
 * The Universal Permissive License (UPL), Version 1.0
 */
/*
 * Your application specific code will go here
 */
define([],
  function () {
    /** 
     * Method for sending notifications to the aria-live region for Accessibility.
     * Sending a notice when the page is loaded, as well as changing the page title
     * is considered best practice for making Single Page Applications Accessbible.
     */
    var validAriaLiveValues = ['off', 'polite', 'assertive'];
    self.announce = function (message, manner) {

      if (manner == undefined || !validAriaLiveValues.includes(manner)) {
        manner = 'polite';
      }
      
      var params = {
        'bubbles': true,
        'detail': { 'message': message, 'manner': manner }
      };
      document.getElementById('globalBody').dispatchEvent(new CustomEvent('announce', params));
    }

    return { announce: announce };
  }
);
