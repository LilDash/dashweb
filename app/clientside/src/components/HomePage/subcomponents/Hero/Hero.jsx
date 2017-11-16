import * as React from 'react';
import './hero.scss';
import PropTypes from 'prop-types';

import { LazyloadImage } from '../../../common/LazyloadImage/LazyloadImage';

export class Hero extends React.Component {
	render() {
		return (
			<div className='hero'>
				<LazyloadImage src={this.props.imageSrc} preloadColor={this.props.preloadColor} />
			</div>
		);
  	}
}

Hero.propTypes = {
	imageSrc: PropTypes.string.isRequired,
	preloadColor: PropTypes.string.isRequired,
};

Hero.defaultProps = {
};