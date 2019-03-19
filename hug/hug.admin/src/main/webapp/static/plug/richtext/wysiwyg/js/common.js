var richtext;
$(document).ready(function(){
	var element = $(".richtext-wysiwyg");
    richtext = $(element).wysiwyg({
    	classes: 'some-more-classes',
        toolbar: "top-selection",
        buttons: {
            insertimage: {
                title: 'Insert image',
                image: '\uf030',
                //showstatic: true,    // wanted on the toolbar
                showselection: false    // wanted on selection
            },
            insertlink: {
                title: 'Insert link',
                image: '\uf08e' // <img src="path/to/image.png" width="16" height="16" alt="" />
            },
            // Fontname plugin
            fontname: {
                title: 'Font',
                image: '\uf031', // <img src="path/to/image.png" width="16" height="16" alt="" />
                popup: function( $popup, $button ) {
                        var list_fontnames = {
                                // Name : Font
                                'Arial, Helvetica' : 'Arial,Helvetica',
                                'Verdana'          : 'Verdana,Geneva',
                                'Georgia'          : 'Georgia',
                                'Courier New'      : 'Courier New,Courier',
                                'Times New Roman'  : 'Times New Roman,Times'
                            };
                        var $list = $('<div/>').addClass('wysiwyg-toolbar-list').attr('unselectable','on');
                        $.each( list_fontnames, function( name, font ){
                            var $link = $('<a/>').attr('href','#')
                                                .css( 'font-family', font )
                                                .html( name )
                                                .click(function(event){
                                                    $(element).wysiwyg('shell').fontName(font).closePopup();
                                                    // prevent link-href-#
                                                    event.stopPropagation();
                                                    event.preventDefault();
                                                    return false;
                                                });
                            $list.append( $link );
                        });
                        $popup.append( $list );
                       },
                //showstatic: true,    // wanted on the toolbar
                showselection: true    // wanted on selection
            },
            // Fontsize plugin
            fontsize: {
                title: 'Size',
                image: '\uf034', // <img src="path/to/image.png" width="16" height="16" alt="" />
                popup: function( $popup, $button ) {
                        var list_fontsizes = {
                            // Name : Size
                            'Huge'    : 7,
                            'Larger'  : 6,
                            'Large'   : 5,
                            'Normal'  : 4,
                            'Small'   : 3,
                            'Smaller' : 2,
                            'Tiny'    : 1
                        };
                        var $list = $('<div/>').addClass('wysiwyg-toolbar-list').attr('unselectable','on');
                        $.each( list_fontsizes, function( name, size ){
                            var $link = $('<a/>').attr('href','#')
                                                .css( 'font-size', (8 + (size * 3)) + 'px' )
                                                .html( name )
                                                .click(function(event){
                                                    $(element).wysiwyg('shell').fontSize(size).closePopup();
                                                    // prevent link-href-#
                                                    event.stopPropagation();
                                                    event.preventDefault();
                                                    return false;
                                                });
                            $list.append( $link );
                        });
                        $popup.append( $list );
                       }
            },
            header: {
                title: 'Header',
                image: '\uf1dc', // <img src="path/to/image.png" width="16" height="16" alt="" />
                popup: function( $popup, $button ) {
                        var list_headers = {
                                // Name : Font
                                'Header 1'     : '<h1>',
                                'Header 2'     : '<h2>',
                                'Header 3'     : '<h3>',
                                'Header 4'     : '<h4>',
                                'Header 5'     : '<h5>',
                                'Header 6'     : '<h6>',
                                'Preformatted' : '<pre>'
                            };
                        var $list = $('<div/>').addClass('wysiwyg-toolbar-list').attr('unselectable','on');
                        $.each( list_headers, function( name, format ){
                            var $link = $('<a/>').attr('href','#').css( 'font-family', format).html(name).click(function(event){
                                $(element).wysiwyg('shell').format(format).closePopup();
                                event.stopPropagation();
                                event.preventDefault();
                                return false;
                            });
                            $list.append( $link );
                        });
                        $popup.append( $list );
                       }
            },
            bold: {
                title: 'Bold (Ctrl+B)',
                image: '\uf032', // <img src="path/to/image.png" width="16" height="16" alt="" />
                hotkey: 'b'
            },
            italic: {
                title: 'Italic (Ctrl+I)',
                image: '\uf033', // <img src="path/to/image.png" width="16" height="16" alt="" />
                hotkey: 'i'
            },
            underline: {
                title: 'Underline (Ctrl+U)',
                image: '\uf0cd', // <img src="path/to/image.png" width="16" height="16" alt="" />
                hotkey: 'u'
            },
            strikethrough: {
                title: 'Strikethrough (Ctrl+S)',
                image: '\uf0cc', // <img src="path/to/image.png" width="16" height="16" alt="" />
                hotkey: 's'
            },
            forecolor: {
                title: 'Text color',
                image: '\uf1fc' // <img src="path/to/image.png" width="16" height="16" alt="" />
            },
            highlight: {
                title: 'Background color',
                image: '\uf043' // <img src="path/to/image.png" width="16" height="16" alt="" />
            },
            alignleft: {
                title: 'Left',
                image: '\uf036', // <img src="path/to/image.png" width="16" height="16" alt="" />
                //showstatic: true,    // wanted on the toolbar
                showselection: false    // wanted on selection
            },
            aligncenter: {
                title: 'Center',
                image: '\uf037', // <img src="path/to/image.png" width="16" height="16" alt="" />
                //showstatic: true,    // wanted on the toolbar
                showselection: false    // wanted on selection
            },
            alignright: {
                title: 'Right',
                image: '\uf038', // <img src="path/to/image.png" width="16" height="16" alt="" />
                //showstatic: true,    // wanted on the toolbar
                showselection: false    // wanted on selection
            },
            alignjustify: {
                title: 'Justify',
                image: '\uf039', // <img src="path/to/image.png" width="16" height="16" alt="" />
                //showstatic: true,    // wanted on the toolbar
                showselection: false    // wanted on selection
            },
            subscript: {
                title: 'Subscript',
                image: '\uf12c', // <img src="path/to/image.png" width="16" height="16" alt="" />
                //showstatic: true,    // wanted on the toolbar
                showselection: true    // wanted on selection
            },
            superscript: {
                title: 'Superscript',
                image: '\uf12b', // <img src="path/to/image.png" width="16" height="16" alt="" />
                //showstatic: true,    // wanted on the toolbar
                showselection: true    // wanted on selection
            },
            indent: {
                title: 'Indent',
                image: '\uf03c', // <img src="path/to/image.png" width="16" height="16" alt="" />
                //showstatic: true,    // wanted on the toolbar
                showselection: false    // wanted on selection
            },
            outdent: {
                title: 'Outdent',
                image: '\uf03b', // <img src="path/to/image.png" width="16" height="16" alt="" />
                //showstatic: true,    // wanted on the toolbar
                showselection: false    // wanted on selection
            },
            orderedList: {
                title: 'Ordered list',
                image: '\uf0cb', // <img src="path/to/image.png" width="16" height="16" alt="" />
                //showstatic: true,    // wanted on the toolbar
                showselection: false    // wanted on selection
            },
            unorderedList: {
                title: 'Unordered list',
                image: '\uf0ca', // <img src="path/to/image.png" width="16" height="16" alt="" />
                //showstatic: true,    // wanted on the toolbar
                showselection: false    // wanted on selection
            },
            removeformat: {
                title: 'Remove format',
                image: '\uf12d' // <img src="path/to/image.png" width="16" height="16" alt="" />
            }
        },
        // Submit-Button
        submit: {
            title: 'Submit',
            image: '\uf00c' // <img src="path/to/image.png" width="16" height="16" alt="" />
        },
        // Other properties
        dropfileclick: 'Drop image or click',
        placeholderUrl: 'www.example.com',
        maxImageSize: [600,200]
    }).change(function(){
        if( typeof console != 'undefined' )
            console.log( 'change' );
    }) .focus(function(){
        if( typeof console != 'undefined' )
            console.log( 'focus' );
    }).blur(function(){
        if( typeof console != 'undefined' )
            console.log( 'blur' );
    });
});
