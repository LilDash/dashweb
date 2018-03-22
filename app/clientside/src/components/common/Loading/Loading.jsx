import * as React from 'react';
import PropTypes from 'prop-types';
import './loading.scss';


class Loading extends React.PureComponent {

	constructor(props) {
	    super(props);
	}

	render() {
		return (
			<div className='loading'><div /><div /><div /></div>
		);
	}
};

Loading.propTypes = {
};

Loading.defaultProps = {
};

export { Loading };