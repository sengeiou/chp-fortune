(function($) {

    BootstrapDialog.prototype.createButton = function(button) {
        var $button = $('<button class="btn"></button>');
        $button.addClass(this.getButtonSize());
        $button.prop('id', button.id);

        // Icon
        if (typeof button.icon !== undefined && $.trim(button.icon) !== '') {
            $button.append(this.createButtonIcon(button.icon));
        }

        // Label
        if (typeof button.label !== undefined) {
            $button.append(button.label);
        }

        // Css class
        if (typeof button.cssClass !== undefined && $.trim(button.cssClass) !== '') {
            $button.addClass(button.cssClass);
        } else {
            $button.addClass('cf_btn-primary');
        }

        // Hotkey
        if (button.hotkey !== undefined) {
            this.registeredButtonHotkeys[button.hotkey] = $button;
        } else {
            // キーが指定されていない場合、ボタンが1の時はEnterを有効にする。
            if (this.options.buttons.length == 1) {
                this.registeredButtonHotkeys[13] = $button; 
            }
        }

        // Button on click
        $button.on('click', {dialog: this, $button: $button, button: button}, function(event) {
            var dialog = event.data.dialog;
            var $button = event.data.$button;
            var button = event.data.button;
            if (typeof button.action === 'function') {
                button.action.call($button, dialog);
            }

            if (button.autospin) {
                $button.toggleSpin(true);
            }
        });

        // Dynamically add extra functions to $button
        this.enhanceButton($button);

        return $button;
    }
    
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_DEFAULT] = '通知';
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_INFO] = '通知';
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_PRIMARY] = '通知';
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_SUCCESS] = '成功';
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_WARNING] = '警告 ';
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_DANGER] = '危险';

})(window.jQuery);
