import React from "react";
import PlaceIcon from "@mui/icons-material/Place";
import AccessTimeIcon from "@mui/icons-material/AccessTime";
import moment from "moment";
import FacebookIcon from "@mui/icons-material/Facebook";
import {Button} from "reactstrap";
import {Link} from "react-router-dom";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";

export class Event extends React.Component<{ currentEvent: T, onClick: () => void }> {
    render() {
        return <div className="card-body">
            <h5 className="card-title">{this.props.currentEvent.name}</h5>
            <p className="card-text" id="tldr">{this.props.currentEvent.tldr}</p>
            <p className="card-text">{this.props.currentEvent.description}</p>
            <p><PlaceIcon/>{this.props.currentEvent.place}
                <AccessTimeIcon/> {moment(this.props.currentEvent.start).format("YYYY-MM-DD HH:mm")} - {moment(this.props.currentEvent.end).format("YYYY-MM-DD HH:mm")}
                <a href={this.props.currentEvent.facebookUrl}><FacebookIcon/></a></p>
            <p><Button tag={Link} to={"/event/" + this.props.currentEvent.id}><EditIcon></EditIcon></Button>
                <Button><DeleteIcon onClick={this.props.onClick}/></Button></p>
        </div>;
    }
}
