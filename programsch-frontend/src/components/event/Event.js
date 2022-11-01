import React, {useEffect, useState} from "react";
import PlaceIcon from "@mui/icons-material/Place";
import AccessTimeIcon from "@mui/icons-material/AccessTime";
import moment from "moment";
import FacebookIcon from "@mui/icons-material/Facebook";
import {Link, useHistory} from "react-router-dom";
import {Box, Stack, Button, Center, Heading, Text, Badge, useColorModeValue, Image} from "@chakra-ui/react";
import {DeleteIcon, EditIcon} from "@chakra-ui/icons";

export function Event(props) {
    const history = useHistory();
    const id = props.currentEvent.id;
    const [currentEvent, setCurrentEvent] = useState(props.currentEvent);
    const boxColor = useColorModeValue('white', 'gray.900');
    const textColor = useColorModeValue('gray.700', 'gray.400');
    const badgeColor = useColorModeValue('gray.50', 'gray.800');

    const setImage = () => {
        setCurrentEvent({...currentEvent, poster: "https://via.placeholder.com/350"});
    }

    const myImage = () => {
        return <Center><Image src={currentEvent.poster} alt="event image" onError={() => setImage()}/> </Center>
    }

    const handleDelete = () => {
        fetch(`http://localhost:8080/api/event/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
        })
        history.push("/");
    }

    const render = () => {

        return <Box>
            {myImage()}
            <Center>
                <Heading style={{marginTop: '15px'}} fontSize={20}>{props.currentEvent.name}</Heading>
            </Center>
            <Text style={{marginTop: '10px'}}>{props.currentEvent.tldr}</Text>
            <Text style={{marginTop: '10px'}} >{props.currentEvent.description}</Text>

            <Stack style={{marginTop: '10px'}} align={'center'} justify={'center'} direction={'row'} >
                <Badge
                    bg={badgeColor}
                    fontWeight={'600'}
                    fontSize={16}>
                    <Box color={textColor}><PlaceIcon/> {props.currentEvent.place}</Box>
                </Badge>
                <Badge
                    bg={badgeColor}
                    fontWeight={'600'}
                    fontSize={16}>
                    <Box color={textColor}><AccessTimeIcon/> {moment(props.currentEvent.start).format("YYYY-MM-DD HH:mm")} - {moment(props.currentEvent.end).format("YYYY-MM-DD HH:mm")}</Box>
                </Badge>
            </Stack>

            <Stack mt={8} direction={'row'} spacing={4}>
                <Button
                    flex={1}
                    leftIcon={<FacebookIcon/>}
                    fontSize={'sm'}
                    rounded={'full'}
                    style={{textDecoration: 'none'}}
                    bg={'blue.400'}
                    boxShadow={
                        '0px 1px 25px -5px rgb(0 0 255 / 40%), 0 10px 10px -5px rgb(0 0 255 / 35%)'
                    }
                    _hover={{
                        bg: 'blue.500',
                    }}
                    _focus={{
                        bg: 'blue.500',
                    }}>
                </Button>
            </Stack>

            <Stack mt={8} direction={'row'} spacing={4}>
                <Button
                    flex={1}
                    as={Link}
                    to={"/event/" + props.currentEvent.id}
                    leftIcon={<EditIcon/>}
                    fontSize={'sm'}
                    rounded={'full'}
                    style={{textDecoration: 'none'}}
                    bg={'yellow.400'}
                    boxShadow={
                        '0px 1px 25px -5px rgb(255 255 0 / 40%), 0 10px 10px -5px rgb(255 255 0 / 35%)'
                    }
                    _hover={{
                        bg: 'yellow.500',
                    }}
                    _focus={{
                        bg: 'yellow.500',
                    }}>
                </Button>
                <Button
                    flex={1}
                    fontSize={'sm'}
                    rounded={'full'}
                    bg={'red.400'}
                    color={'white'}
                    onClick={() => handleDelete()}
                    boxShadow={
                        '0px 1px 25px -5px rgb(255 0 0 / 40%), 0 10px 10px -5px rgb(255 0 0 / 35%)'
                    }
                    _hover={{
                        bg: 'red.500',
                    }}
                    _focus={{
                        bg: 'red.500',
                    }}>
                    <DeleteIcon/>
                </Button>
            </Stack>
        </Box>;
    }

    return render(props);
}
export default Event;
