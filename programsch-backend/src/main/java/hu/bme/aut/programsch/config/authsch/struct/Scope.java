package hu.bme.aut.programsch.config.authsch.struct;

import com.fasterxml.jackson.databind.JsonNode;
import hu.bme.aut.programsch.config.authsch.response.ProfileDataResponse.ProfileDataResponseBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public enum Scope {

    BASIC("basic") {
        @Override
        public boolean canApply(JsonNode obj) {
            return true;
        }

        @Override
        public void apply(ProfileDataResponseBuilder response, JsonNode obj) {
            response.setInternalId(UUID.fromString(obj.get("internal_id").asText()));
        }
    },

    DISPLAY_NAME("displayName") {
        @Override
        public void apply(ProfileDataResponseBuilder response, JsonNode obj) {
            response.setDisplayName(obj.get(getScope()).asText());
        }
    },

    SURNAME("sn") {
        @Override
        public void apply(ProfileDataResponseBuilder response, JsonNode obj) {
            response.setSurname(obj.get(getScope()).asText());
        }
    },

    GIVEN_NAME("givenName") {
        @Override
        public void apply(ProfileDataResponseBuilder response, JsonNode obj) {
            response.setGivenName(obj.get(getScope()).asText());
        }
    },

    MAIL("mail") {
        @Override
        public void apply(ProfileDataResponseBuilder response, JsonNode obj) {
            response.setMail(obj.get(getScope()).asText());
        }
    },

    NEPTUN_CODE("niifPersonOrgID") {
        @Override
        public void apply(ProfileDataResponseBuilder response, JsonNode obj) {
            response.setNeptun(obj.get(getScope()).asText());
        }
    },

    LINKED_ACCOUNTS("linkedAccounts") {
        @Override
        public void apply(ProfileDataResponseBuilder response, JsonNode obj) {
            obj.path(getScope()).fields()
                    .forEachRemaining(x -> response.addLinkedAccount(x.getKey(), x.getValue().asText()));
        }
    },

    EDU_PERSON_ENTILEMENT("eduPersonEntitlement") {
        @Override
        public void apply(ProfileDataResponseBuilder response, JsonNode obj) {
            obj.path(getScope()).elements().forEachRemaining(entrant ->
                    response.addEduPersonEntitlement(new PersonEntitlement(
                            entrant.get("id").asInt(),
                            entrant.get("name").asText(),
                            entrant.get("status").asText(),
                            entrant.get("start").asText(),
                            entrant.get("end").isNull() ? null : entrant.get("end").asText()))
            );
        }
    },

    @Deprecated
    ROOM_NUMBER("roomNumber") {
        @Override
        public void apply(ProfileDataResponseBuilder response, JsonNode obj) {
            response.setRoomNumber(obj.get(getScope()).asText());
        }
    },

    MOBILE("mobile") {
        @Override
        public void apply(ProfileDataResponseBuilder response, JsonNode obj) {
            response.setMobile(obj.get(getScope()).asText());
        }
    },

    COURSES("niifEduPersonAttendedCourse") {
        @Override
        public void apply(ProfileDataResponseBuilder response, JsonNode obj) {
            for (String course : obj.get(Scope.COURSES.getScope()).asText().split(";"))
                response.addCourse(course);
        }
    },

    ENTRANTS("entrants") {
        @Override
        public void apply(ProfileDataResponseBuilder response, JsonNode obj) {
            obj.path(Scope.ENTRANTS.getScope()).elements().forEachRemaining(entrant ->
                    response.addEntrant(new Entrant(
                            entrant.get("groupId").asInt(),
                            entrant.get("groupName").asText(),
                            entrant.get("entrantType").asText()))
            );
        }
    },

    ACTIVE_DIRECTORY_MEMBERSHIP("admembership") {
        @Override
        public void apply(ProfileDataResponseBuilder response, JsonNode obj) {
            obj.path(Scope.ACTIVE_DIRECTORY_MEMBERSHIP.getScope()).elements()
                    .forEachRemaining(x -> response.addADMembership(x.asText()));
        }
    },

    BME_UNIT_SCOPE("bmeunitscope") {
        @Override
        public void apply(ProfileDataResponseBuilder response, JsonNode obj) {
            obj.path(Scope.BME_UNIT_SCOPE.getScope()).elements()
                    .forEachRemaining(x -> response.addBmeUnitScope(BMEUnitScope.valueOf(x.asText())));
        }
    };

    private final String scope;

    private Scope(String scope) {
        this.scope = scope;
    }

    public static Scope byScope(String scope) {
        for (Scope s : values())
            if (s.getScope().equals(scope))
                return s;
        return BASIC;
    }

    public String getScope() {
        return scope;
    }

    public static String buildForUrl(List<Scope> scopes) {
        return String.join("+", scopes.stream()
                .map(x -> x.scope).collect(Collectors.toList()));
    }

    public static String buildForUrl(Scope... scopes) {
        return String.join("+", Arrays.asList(scopes).stream()
                .map(x -> x.scope).collect(Collectors.toList()));
    }

    public static List<Scope> listFromString(String delimiter, String scopes) {
        return Arrays.asList(scopes.split(delimiter)).stream()
                .map(Scope::byScope)
                .collect(Collectors.toList());
    }

    public boolean canApply(JsonNode obj) {
        return obj.get(getScope()) != null
                && !obj.get(getScope()).isNull();
    }

    public abstract void apply(ProfileDataResponseBuilder builder, JsonNode obj);

}