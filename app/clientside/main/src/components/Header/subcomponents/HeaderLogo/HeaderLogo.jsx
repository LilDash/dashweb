import * as React from 'react';
import './header-logo.scss';
import PropTypes from 'prop-types';


export class HeaderLogo extends React.PureComponent {
	render() {
		return (
			<div className='header-logo'>
				<span>D-Planet</span>
			</div>
		);
  	}
}

// homeParams.propTypes = {
// 	imageSrc: PropTypes.string.isRequired,
// 	preloadColor: PropTypes.string.isRequired,
// };

// homeParams.defaultProps = {
// 	preloadColor: '#000000',
// };