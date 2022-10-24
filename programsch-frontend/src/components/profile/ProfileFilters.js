import React, {useEffect, useState} from "react";
import {Button} from "reactstrap";
import {Link} from "react-router-dom";
import './ProfileFilter.css';
import {Stack} from "@chakra-ui/react";

export function ProfileFilters(){



    const renderFilters = () => {
    return                     <Stack mt={8} direction={'row'} spacing={4}>
        <Button
            flex={1}
            fontSize={'sm'}
            rounded={'full'}
            _focus={{
                bg: 'gray.200',
            }}>
            Message
        </Button>
        <Button
            flex={1}
            fontSize={'sm'}
            rounded={'full'}
            bg={'blue.400'}
            color={'white'}
            boxShadow={
                '0px 1px 25px -5px rgb(66 153 225 / 48%), 0 10px 10px -5px rgb(66 153 225 / 43%)'
            }
            _hover={{
                bg: 'blue.500',
            }}
            _focus={{
                bg: 'blue.500',
            }}>
            Follow
        </Button>
    </Stack>
    }

    return renderFilters();
}

export default ProfileFilters;
