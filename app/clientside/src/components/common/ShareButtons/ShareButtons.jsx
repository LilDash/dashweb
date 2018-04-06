import * as React from 'react';
import PropTypes from 'prop-types';
import './share-buttons.scss';

class ShareButtons extends React.Component {

	constructor(props) {
	    super(props);
	    this.state = {
		
		  
	    };
	}


	render() {
		return (
            <div className="share-buttons">
                <div><label>{'分享到:'}</label></div>
                <div className="jiathis_style_32x32">
                    <a className="jiathis_button_weixin" />
                    <a className="jiathis_button_tsina" />
                    <a className="jiathis_button_qzone" />
                    <a className="jiathis_button_douban" />
                    <a className="jiathis_button_tqq" />
                    <a className="jiathis_button_fb" />
                    <a className="jiathis_button_twitter" />
                    <a className="jiathis_button_fav" />
                    <a href="http://www.jiathis.com/share" className="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank" />
                </div>
            </div>
		);
	}
};

ShareButtons.propTypes = {
	
};

ShareButtons.defaultProps = {
  	
};

export { ShareButtons };