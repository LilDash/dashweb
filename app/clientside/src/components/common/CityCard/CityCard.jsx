import * as React from 'react';
import './city-card.scss';
import PropTypes from 'prop-types';
import { CenterCroppedImage } from '../CenterCroppedImage/CenterCroppedImage';

export class CityCard extends React.Component {
	render() {
		return (
			<div className='city-card'>             
				<CenterCroppedImage 
					className='city-card-image' 
					src={ 'http://p0e2yp82n.bkt.clouddn.com/FmlWgL0829TTKcTxSkCKfFyoTmuL' }
					isLazyload={ true }
					preloadColor={ '#99CCFF' }
				/>
                <div className='city-info'> 
                    <p>圣以撒主教座堂</p>
                </div>
			</div>
		);
  	}
}

CityCard.propTypes = {
	
};

CityCard.defaultProps = {
};